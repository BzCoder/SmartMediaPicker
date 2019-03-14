package com.bzcoder.mediapicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.bzcoder.mediapicker.R;
import me.bzcoder.mediapicker.camera.CameraDialogUtil;

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
                CameraDialogUtil.builder(getSupportFragmentManager())
                        .withMaxImageSelectable(5)
                        .withMaxVideoSelectable(1)
                        .withCountable(true)
                        .withMaxVideoLength(15 * 1000)
                        .withMaxVideoSize(1)
                        .withMaxHeight(1920)
                        .withMaxImageSize(5)
                        .withMaxWidth(1920)
                        .build()
                        .show();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<String> resultData = new ArrayList<>();
        resultData.addAll(CameraDialogUtil.getResultData(this, requestCode, resultCode, data));
        tv_path.setText(Arrays.toString(resultData.toArray()));
    }
}
