package com.bzcoder.mediapicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.bzcoder.mediapicker.R;
import me.bzcoder.mediapicker.camera.SmartMediaPicker;
import me.bzcoder.mediapicker.config.MediaPickerEnum;

public class SampleFragment extends Fragment implements View.OnClickListener {
    private TextView tvPath;
    private Button btnPath;
    private Button btnStartCamera;
    private Button btnStartPhoto;
    private SmartMediaPicker.Builder builder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        tvPath = getView().findViewById(R.id.tv_path);
        tvPath.setOnClickListener(this);
        btnPath = getView().findViewById(R.id.btn_path);
        btnPath.setOnClickListener(this);
        btnStartCamera = getView().findViewById(R.id.btn_start_camera);
        btnStartCamera.setOnClickListener(this);
        btnStartPhoto = getView().findViewById(R.id.btn_start_photo);
        btnStartPhoto.setOnClickListener(this);

        builder = SmartMediaPicker.builder(this)
                //最大图片选择数目
                .withMaxImageSelectable(5)
                //最大视频选择数目
                .withMaxVideoSelectable(1)
                //图片选择器是否显示数字
                .withCountable(true)
                //最大视频长度
                .withMaxVideoLength(15 * 1000)
                //最大视频文件大小 单位MB
                .withMaxVideoSize(1)
                //最大图片高度 默认1920
                .withMaxHeight(1920)
                //最大图片宽度 默认1920
                .withMaxWidth(1920)
                //最大图片大小 单位MB
                .withMaxImageSize(5)
                //设置图片加载引擎
                .withImageEngine(new Glide4Engine());
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

            default:
                break;

        }
    }
}
