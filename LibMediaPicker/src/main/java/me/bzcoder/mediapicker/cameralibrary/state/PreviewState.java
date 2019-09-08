package me.bzcoder.mediapicker.cameralibrary.state;

import android.graphics.Bitmap;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.daasuu.mp4compose.FillMode;
import com.daasuu.mp4compose.composer.Mp4Composer;

import me.bzcoder.mediapicker.cameralibrary.CameraInterface;
import me.bzcoder.mediapicker.cameralibrary.JCameraView;
import me.bzcoder.mediapicker.cameralibrary.util.FileUtil;
import me.bzcoder.mediapicker.cameralibrary.util.LogUtil;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/9/8
 * 描    述：空闲状态
 * =====================================
 */
class PreviewState implements State {
    public static final String TAG = "PreviewState";

    private CameraMachine machine;

    PreviewState(CameraMachine machine) {
        this.machine = machine;
    }

    @Override
    public void start(SurfaceHolder holder, float screenProp) {
        CameraInterface.getInstance().doStartPreview(holder, screenProp);
    }

    @Override
    public void stop() {
        CameraInterface.getInstance().doStopPreview();
    }


    @Override
    public void focus(float x, float y, CameraInterface.FocusCallback callback) {
        LogUtil.i("preview state focus");
        if (machine.getView().handlerFocus(x, y)) {
            CameraInterface.getInstance().handleFocus(machine.getContext(), x, y, callback);
        }
    }

    @Override
    public void switich(SurfaceHolder holder, float screenProp) {
        CameraInterface.getInstance().switchCamera(holder, screenProp);
    }

    @Override
    public void restart() {

    }

    @Override
    public void capture() {
        CameraInterface.getInstance().takePicture((bitmap, isVertical) -> {
            machine.getView().showPicture(bitmap, isVertical);
            machine.setState(machine.getBorrowPictureState());
            LogUtil.i("capture");
        });
    }

    @Override
    public void record(Surface surface, float screenProp) {
        CameraInterface.getInstance().startRecord(surface, screenProp, null);
    }

    @Override
    public void stopRecord(final boolean isShort, long time) {
        CameraInterface.getInstance().stopRecord(isShort, (url, firstFrame) -> {
            if (isShort) {
                machine.getView().resetState(JCameraView.TYPE_SHORT);
            }
            else if (CameraInterface.getInstance().isMirror()  ) {
                flipHorizontalVideo(url, firstFrame);
            } else {
                machine.getView().playVideo(firstFrame, url);
                machine.setState(machine.getBorrowVideoState());
            }
        });
    }


    @Override
    public void cancel(SurfaceHolder holder, float screenProp) {
        LogUtil.i("浏览状态下,没有 cancel 事件");
    }

    @Override
    public void confirm() {
        LogUtil.i("浏览状态下,没有 confirm 事件");
    }

    @Override
    public void zoom(float zoom, int type) {
        LogUtil.i(TAG, "zoom");
        CameraInterface.getInstance().setZoom(zoom, type);
    }

    @Override
    public void flash(String mode) {
        CameraInterface.getInstance().setFlashMode(mode);
    }


    private void flipHorizontalVideo(String url, Bitmap firstFrame) {
        String convert_url = url.replace("video", "covert_video");
        new Mp4Composer(url, convert_url)
                .flipHorizontal(true)
                .fillMode(FillMode.PRESERVE_ASPECT_FIT)
                .listener(new Mp4Composer.Listener() {
                    @Override
                    public void onProgress(double progress) {
                    }

                    @Override
                    public void onCompleted() {
                        FileUtil.deleteFile(url);
                        machine.getView().playVideo(firstFrame, convert_url);
                        machine.setState(machine.getBorrowVideoState());
                    }

                    @Override
                    public void onCanceled() {
                    }

                    @Override
                    public void onFailed(Exception exception) {
                    }
                })
                .start();
    }
}
