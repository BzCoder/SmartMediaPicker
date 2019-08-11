package com.bzcoder.mediapicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import me.bzcoder.mediapicker.SmartMediaPicker;
import me.bzcoder.mediapicker.config.MediaPickerEnum;
import me.bzcoder.mediapicker.sample.R;

/**
 * @author : BaoZhou
 * @date : 2019/3/26 15:21
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPath;
    private Button btnPath;
    private Button btnStartCamera;
    private Button btnStartPhoto;
    private SmartMediaPicker.Builder builder;
    private Button jumpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvPath = findViewById(R.id.tv_path);
        tvPath.setOnClickListener(this);
        btnPath = findViewById(R.id.btn_path);
        btnPath.setOnClickListener(this);
        btnStartCamera = findViewById(R.id.btn_start_camera);
        btnStartCamera.setOnClickListener(this);
        btnStartPhoto = findViewById(R.id.btn_start_photo);
        btnStartPhoto.setOnClickListener(this);

        builder = SmartMediaPicker.builder(this)
                //最大图片选择数目
                .withMaxImageSelectable(0)
                //最大视频选择数目
                .withMaxVideoSelectable(1)
                //图片选择器是否显示数字
                .withCountable(true)
                //最大视频长度
                .withMaxVideoLength(5 * 1000)
                //最大视频文件大小 单位MB
                .withMaxVideoSize(1)
                //最大图片高度 默认1920
                .withMaxHeight(1920)
                //最大图片宽度 默认1920
                .withMaxWidth(1920)
                //最大图片大小 单位MB
                .withMaxImageSize(5)
                //前置摄像头拍摄是否镜像翻转图像 默认为true 与微信一致的话为false
                .withIsMirror(true)
                //设置图片加载引擎
                .withImageEngine(new Glide4Engine());
        jumpActivity = (Button) findViewById(R.id.jump_activity);
        jumpActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_path:
                builder.withMediaPickerType(MediaPickerEnum.BOTH)
                        .build()
                        .show();
                break;
            case R.id.btn_start_camera:
                builder.withMediaPickerType(MediaPickerEnum.CAMERA)
                        .build()
                        .show();
                break;
            case R.id.btn_start_photo:
                builder.withMediaPickerType(MediaPickerEnum.PHOTO_PICKER)
                        .build()
                        .show();
                break;
            case R.id.jump_activity:
                Intent intent = new Intent(MainActivity.this, SampleFragmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<String> resultData = SmartMediaPicker.getResultData(this, requestCode, resultCode, data);
        if (resultData != null && resultData.size() > 0) {
            tvPath.setText(Arrays.toString(resultData.toArray()) + "\n文件类型："
                    + SmartMediaPicker.getFileType(resultData.get(0)) + "\n视频时长: " +
                    (SmartMediaPicker.getFileType(resultData.get(0)).contains("video") ?
                            SmartMediaPicker.getVideoDuration(resultData.get(0)) : ""));

        } else {
            tvPath.setText("NO DATA");
        }
    }
}
