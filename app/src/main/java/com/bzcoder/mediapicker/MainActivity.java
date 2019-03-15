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

import me.bzcoder.mediapicker.R;
import me.bzcoder.mediapicker.camera.SmartMediaPicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_path;
    private Button btn_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_path = findViewById(R.id.tv_path);
        btn_path = findViewById(R.id.btn_path);
        btn_path.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_path:
                SmartMediaPicker.builder(getSupportFragmentManager())
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
                        .build()
                        .show();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<String> resultData = SmartMediaPicker.getResultData(this, requestCode, resultCode, data);
        if(resultData!=null && resultData.size()>0){
            tv_path.setText(Arrays.toString(resultData.toArray()));
        }
        else{
            tv_path.setText("NO DATA");
        }
    }
}
