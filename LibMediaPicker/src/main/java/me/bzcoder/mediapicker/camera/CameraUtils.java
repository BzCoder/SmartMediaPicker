package me.bzcoder.mediapicker.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import me.bzcoder.mediapicker.cameralibrary.JCameraView;

import static me.bzcoder.mediapicker.camera.Constant.BUTTON_STATE;

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
    private static Activity mActivity;
    private static int buttonState = JCameraView.BUTTON_STATE_BOTH;
    public static void startCamera(Activity activity, Context context) {
        startCamera(activity, context,JCameraView.BUTTON_STATE_BOTH);
    }

    public static void startCamera(Activity activity, Context context, int state) {
        mActivity = activity;
        mContext = context;
        buttonState = state;
        getPermissions();
    }

    /**
     * 获取权限
     */
    private static void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                    .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO) == PackageManager
                            .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager
                            .PERMISSION_GRANTED) {
                startActivity(mActivity);
            } else {
                Toast.makeText(mContext, "请确认开启录音，相机，读写存储权限", Toast.LENGTH_SHORT).show();
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivity(mActivity);
        }
    }

    private static void startActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, CameraActivity.class);
        intent.putExtra(BUTTON_STATE, buttonState);
        activity.startActivityForResult(intent,Constant.CAMERA_RESULT_CODE);
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
                    startActivity(mActivity);
                } else {
                    Toast.makeText(mContext, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
