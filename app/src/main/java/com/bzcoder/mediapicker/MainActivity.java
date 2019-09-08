package com.bzcoder.mediapicker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private EditText editMaxImage;
    private EditText editMaxVideo;
    private EditText editMaxVideoLength;
    private EditText editMaxVideoSize;
    private EditText editMaxImageHeight;
    private EditText editMaxImageWidth;
    private EditText editMaxImageSize;
    private SwitchCompat switchMirror;
    private SwitchCompat switchCountNumber;
    private RadioButton radioDialog;
    private RadioButton radioCamera;
    private RadioButton radioPhoto;
    private RadioGroup radioGroupStrategy;
    private Button jumpActivity;
    private Button start;
    private MediaPickerEnum mediaPickerType = MediaPickerEnum.BOTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvPath = (TextView) findViewById(R.id.tv_path);
        tvPath.setOnClickListener(this);
        editMaxImage = (EditText) findViewById(R.id.edit_max_image);
        editMaxImage.setOnClickListener(this);
        editMaxVideo = (EditText) findViewById(R.id.edit_max_video);
        editMaxVideo.setOnClickListener(this);
        editMaxVideoLength = (EditText) findViewById(R.id.edit_max_video_length);
        editMaxVideoLength.setOnClickListener(this);
        editMaxVideoSize = (EditText) findViewById(R.id.edit_max_video_size);
        editMaxVideoSize.setOnClickListener(this);
        editMaxImageHeight = (EditText) findViewById(R.id.edit_max_image_height);
        editMaxImageHeight.setOnClickListener(this);
        editMaxImageWidth = (EditText) findViewById(R.id.edit_max_image_width);
        editMaxImageWidth.setOnClickListener(this);
        editMaxImageSize = (EditText) findViewById(R.id.edit_max_image_size);
        editMaxImageSize.setOnClickListener(this);
        switchMirror = (SwitchCompat) findViewById(R.id.switchMirror);
        switchMirror.setOnClickListener(this);
        switchCountNumber = (SwitchCompat) findViewById(R.id.switchCountNumber);
        switchCountNumber.setOnClickListener(this);
        radioDialog = (RadioButton) findViewById(R.id.radioDialog);
        radioDialog.setOnClickListener(this);
        radioCamera = (RadioButton) findViewById(R.id.radioCamera);
        radioCamera.setOnClickListener(this);
        radioPhoto = (RadioButton) findViewById(R.id.radioPhotoPicker);
        radioPhoto.setOnClickListener(this);
        radioGroupStrategy = (RadioGroup) findViewById(R.id.radioGroupStrategy);
        radioGroupStrategy.setOnClickListener(this);
        jumpActivity = (Button) findViewById(R.id.jump_activity);
        jumpActivity.setOnClickListener(this);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        radioGroupStrategy.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioDialog:
                    mediaPickerType = MediaPickerEnum.BOTH;
                    break;
                case R.id.radioCamera:
                    mediaPickerType = MediaPickerEnum.CAMERA;
                    break;
                case R.id.radioPhotoPicker:
                    mediaPickerType = MediaPickerEnum.PHOTO_PICKER;
                    break;
                default:
                    mediaPickerType = MediaPickerEnum.BOTH;
                    break;
            }
        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump_activity:
                Intent intent = new Intent(this, SampleFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.start:
                SmartMediaPicker.builder(this)
                        //最大图片选择数目
                        .withMaxImageSelectable(Integer.parseInt(editMaxImage.getText().toString()))
                        //最大视频选择数目
                        .withMaxVideoSelectable(Integer.parseInt(editMaxVideo.getText().toString()))
                        //图片选择器是否显示数字
                        .withCountable(switchCountNumber.isChecked())
                        //最大视频长度 单位ms
                        .withMaxVideoLength(Integer.parseInt(editMaxVideoLength.getText().toString()))
                        //最大视频文件大小 单位MB
                        .withMaxVideoSize(Integer.parseInt(editMaxVideoSize.getText().toString()))
                        //最大图片高度 默认1920
                        .withMaxHeight(Integer.parseInt(editMaxImageHeight.getText().toString()))
                        //最大图片宽度 默认1920
                        .withMaxWidth(Integer.parseInt(editMaxImageWidth.getText().toString()))
                        //最大图片大小 单位MB
                        .withMaxImageSize(Integer.parseInt(editMaxImageSize.getText().toString()))
                        //前置摄像头拍摄是否开启镜像翻转调整图像 默认为true
                        .withIsMirror(switchMirror.isChecked())
                        //设置图片加载引擎
                        .withImageEngine(new Glide4Engine())
                        //选择类型
                        .withMediaPickerType(mediaPickerType)
                        .build()
                        .show();
                break;
            default:
                break;
        }
    }

}
