package me.bzcoder.mediapicker.photopicker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * 
 * @author : BaoZhou
 * @date : 2019/3/15 0:14
 */
public class PermissionUtils {
    public static final int MY_PERMISSIONS_REQUEST_CODE=10001;

    public static boolean checkAudioPermission(Context context){  //检查麦克风权限 ,写入文件，读出文件权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            //申请RECORD_AUDIO  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请RECORD_AUDIO  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请RECORD_AUDIO  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            //权限已经允许   请执行下一步操作
//            Toast.makeText(context,"Permission Granted",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public static boolean checkCameraPermission(Context context){  //检查拍照权限 ,写入文件，读出文件权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            return true;
        }
        return false;
    }

    public static boolean checkCameraScanPermission(Context context){  //检查拍照权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            return true;
        }
        return false;
    }

    public static boolean checkExternalStoragePermission(Context context){  //检查写入文件，读出文件权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //CAMERA  WRITE_EXTERNAL_STORAGE  READ_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            return true;
        }
        return false;
    }

    public static boolean checkLocationPermission(Context context){  //检查获取位置权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //ACCESS_COARSE_LOCATION  ACCESS_FINE_LOCATION 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //ACCESS_COARSE_LOCATION  ACCESS_FINE_LOCATION 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            //权限已经允许   请执行下一步操作
//            Toast.makeText(context,"Permission Granted",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public static boolean checkContactsPermission(Context context){  //检查获取联系人权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //READ_CONTACTS  GET_ACCOUNTS 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    public static boolean checkSendSMSPermission(Context context){  //检查获取发短信权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            //SEND_SMS 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            //权限已经允许   请执行下一步操作
//            Toast.makeText(context,"Permission Granted",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public static boolean checkPhonePermission(Context context){  //检查获取电话权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //READ_PHONE_STATE  CALL_PHONE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //READ_PHONE_STATE  CALL_PHONE 权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CODE);
        }else{
            //权限已经允许   请执行下一步操作
//            Toast.makeText(context,"Permission Granted",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
