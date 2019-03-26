package me.bzcoder.mediapicker.camera;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import me.bzcoder.mediapicker.R;
import me.bzcoder.mediapicker.cameralibrary.JCameraView;
import me.bzcoder.mediapicker.cameralibrary.listener.ClickListener;
import me.bzcoder.mediapicker.cameralibrary.listener.ErrorListener;
import me.bzcoder.mediapicker.cameralibrary.listener.JCameraListener;
import me.bzcoder.mediapicker.cameralibrary.util.FileUtil;
import me.bzcoder.mediapicker.config.Constant;


import java.io.File;
import java.util.ArrayList;


/**
 * 录像照相
 *
 * @author : BaoZhou
 * @date : 2018/7/12 21:40
 */
public class CameraActivity extends AppCompatActivity {
    private JCameraView jCameraView;
    /**
     * BUTTON_STATE_ONLY_CAPTURE = 0x101;      //只能拍照
     * BUTTON_STATE_ONLY_RECORDER = 0x102;     //只能录像
     * BUTTON_STATE_BOTH = 0x103;              //两者都可以
     */
    public int buttonState;

    public int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonState = getIntent().getIntExtra(Constant.BUTTON_STATE, JCameraView.BUTTON_STATE_BOTH);
        duration = getIntent().getIntExtra(Constant.DURATION, 10*1000);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.camera_layout);
        jCameraView = findViewById(R.id.jcameraview);
        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        jCameraView.setDuration(duration);
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //错误监听
                Intent intent = new Intent();
                setResult(103, intent);
                finishActivityWithAnim();
            }

            @Override
            public void AudioPermissionError() {
                Toast.makeText(CameraActivity.this, "给点录音权限可以?", Toast.LENGTH_SHORT).show();
            }
        });
        if (buttonState != 0)
            jCameraView.setFeatures(buttonState);
        else {
            jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        }
        //JCameraView监听
        if (JCameraView.BUTTON_STATE_ONLY_CAPTURE == buttonState) {//只拍照
            jCameraView.setTip("轻触拍照");
        } else if (JCameraView.BUTTON_STATE_ONLY_RECORDER == buttonState) {//只拍摄
            jCameraView.setTip("长按拍照");
        } else {
            jCameraView.setTip("轻触拍照，长按录制视频");
        }
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
                String path = FileUtil.saveBitmap("capture_photo", bitmap);
                ArrayList<String> paths = new ArrayList<>(1);
                paths.add(path);
                Intent intent = new Intent();
                intent.putExtra(Constant.CAMERA_PATH, paths);
                setResult(RESULT_OK, intent);
                finishActivityWithAnim();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //获取视频路径
                ArrayList<String> paths = new ArrayList<>(1);
                paths.add(url);
                Intent intent = new Intent();
                intent.putExtra(Constant.CAMERA_PATH, paths);
                setResult(RESULT_OK, intent);
                finishActivityWithAnim();
            }

        });

        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                CameraActivity.this.finishActivityWithAnim();
            }
        });
        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(CameraActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }

    private void finishActivityWithAnim() {
        finish();
        overridePendingTransition(0, R.anim.camera_push_bottom_out);
    }

    @Override
    public void onBackPressed() {
        finishActivityWithAnim();
    }
}
