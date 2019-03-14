package com.bzcoder.mediapicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhihu.matisse.Matisse;

import java.util.Arrays;
import java.util.List;

import me.bzcoder.mediapicker.R;
import me.bzcoder.mediapicker.camera.CameraDialogUtil;
import me.bzcoder.mediapicker.camera.Constant;
import me.bzcoder.mediapicker.cameralibrary.util.LogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<String> result = null;
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
                CameraDialogUtil.showCameraDialog(getSupportFragmentManager());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constant.REQUEST_CODE_CHOOSE) {
            result = Matisse.obtainPathResult(data);
        } else if (resultCode == Constant.CAMERA_RESULT_CODE) {
            result = data.getStringArrayListExtra(Constant.CAMERA_PATH);
        }
        tv_path.setText(Arrays.toString(result.toArray()));
        if (resultCode == Constant.CAMERA_ERROR_CODE) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }
    }
}
