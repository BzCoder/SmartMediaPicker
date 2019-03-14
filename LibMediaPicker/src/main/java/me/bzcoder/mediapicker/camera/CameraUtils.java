package me.bzcoder.mediapicker.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.bzcoder.mediapicker.cameralibrary.JCameraView;
import me.bzcoder.mediapicker.config.Constant;

/**
 * 拍照相机工具类
 *
 * @author : BaoZhou
 * @date : 2018/7/12 21:48
 */
public class CameraUtils {
    //权限申请自定义码
    private static final int GET_PERMISSION_REQUEST = 100;
    private static Context mContext;
    private static Fragment mFragment;
    private static int buttonState = JCameraView.BUTTON_STATE_BOTH;
    private static int mDuration;


    public static void startCamera(final Fragment fragment, Context context, int state, int duration) {
        mFragment = fragment;
        mContext = context;
        buttonState = state;
        mDuration = duration;

        RxPermissions rxPermissions = new RxPermissions(fragment);
        rxPermissions.request(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean){
                    startActivity(fragment.getActivity());
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "请确认开启录音，相机，读写存储权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });


    }


    private static void startActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, CameraActivity.class);
        intent.putExtra(Constant.BUTTON_STATE, buttonState);
        intent.putExtra(Constant.DURATION, mDuration);
        activity.startActivityForResult(intent, Constant.CAMERA_RESULT_CODE);
    }


    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    startActivity(mFragment.getActivity());
                } else {
                    Toast.makeText(mContext, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
