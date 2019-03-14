package me.bzcoder.mediapicker.camera;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import me.bzcoder.mediapicker.config.Constant;
import me.bzcoder.mediapicker.config.MediaPickerConfig;

import static android.app.Activity.RESULT_OK;


/**
 * @author : BaoZhou
 * @date : 2019/3/14 21:38
 */
public class CameraDialogUtil {
    private volatile static CameraDialogUtil instance;
    private volatile static CameraDialogFragment cameraDialogFragment;
    private FragmentManager manager;
    private MediaPickerConfig config;

    private CameraDialogUtil() {
        if (cameraDialogFragment == null) {
            cameraDialogFragment = new CameraDialogFragment();
        }
    }

    public void show() {
        cameraDialogFragment.setConfig(config);
        cameraDialogFragment.show(manager, "cameraDialogFragment");
    }

    public static CameraDialogUtil getInstance() {
        if (instance == null) {
            synchronized (CameraDialogUtil.class) {
                if (instance == null) {
                    instance = new CameraDialogUtil();
                }
            }
        }
        return instance;
    }

    public static List<String> getResultData(Context context, int requestCode, int resultCode, @Nullable Intent data) {
        List<String> result = new ArrayList<>();
        if (resultCode == RESULT_OK && requestCode == Constant.REQUEST_CODE_CHOOSE) {
            result = Matisse.obtainPathResult(data);
        } else if (resultCode == Constant.CAMERA_RESULT_CODE) {
            result = data.getStringArrayListExtra(Constant.CAMERA_PATH);
        }
        if (resultCode == Constant.CAMERA_ERROR_CODE) {
            Toast.makeText(context, "请检查相机权限", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public static Builder builder(FragmentManager manager) {
        return new Builder(manager);
    }

    private void setConfig(MediaPickerConfig config) {
        this.config = config;
    }


    /**
     * 设置各参数默认值
     */
    public static class Builder {
        private FragmentManager manager;
        private boolean countable;
        private boolean originalEnable;
        private int maxOriginalSize;
        private int maxImageSelectable;
        private int maxVideoSelectable;
        private int maxWidth;
        private int maxHeight;
        private int maxImageSize;
        private int maxVideoLength;
        private int maxVideoSize;

        private Builder(FragmentManager manager) {
            this.manager = manager;
            originalEnable = false;
            maxOriginalSize = 15;
            maxImageSelectable = 9;
            maxVideoSelectable = 1;
            maxWidth = 1920;
            maxHeight = 1920;
            maxImageSize = 15;
            maxVideoLength = 20000;
            maxVideoSize = 20;
        }

        public Builder withManager(FragmentManager manager) {
            this.manager = manager;
            return this;
        }

        public Builder withCountable(boolean countable) {
            this.countable = countable;
            return this;
        }

        public Builder withOriginalEnable(boolean originalEnable) {
            this.originalEnable = originalEnable;
            return this;
        }

        public Builder withMaxOriginalSize(int maxOriginalSize) {
            this.maxOriginalSize = maxOriginalSize;
            return this;
        }

        public Builder withMaxImageSelectable(int maxImageSelectable) {
            this.maxImageSelectable = maxImageSelectable;
            return this;
        }

        public Builder withMaxVideoSelectable(int maxVideoSelectable) {
            this.maxVideoSelectable = maxVideoSelectable;
            return this;
        }

        public Builder withMaxWidth(int maxWidth) {
            this.maxWidth = maxWidth;
            return this;
        }

        public Builder withMaxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public Builder withMaxImageSize(int maxImageSize) {
            this.maxImageSize = maxImageSize;
            return this;
        }

        public Builder withMaxVideoLength(int maxVideoLength) {
            this.maxVideoLength = maxVideoLength;
            return this;
        }

        public Builder withMaxVideoSize(int maxVideoSize) {
            this.maxVideoSize = maxVideoSize;
            return this;
        }

        public CameraDialogUtil build() {
            CameraDialogUtil cameraDialogUtil = CameraDialogUtil.getInstance();
            MediaPickerConfig config = new MediaPickerConfig();
            cameraDialogUtil.manager = manager;
            config.setCountable(countable);
            config.setOriginalEnable(originalEnable);
            config.setMaxOriginalSize(maxOriginalSize);
            config.setMaxImageSelectable(maxImageSelectable);
            config.setMaxVideoSelectable(maxVideoSelectable);
            config.setMaxWidth(maxWidth);
            config.setMaxHeight(maxHeight);
            config.setMaxImageSize(maxImageSize);
            config.setMaxVideoLength(maxVideoLength);
            config.setMaxVideoSize(maxVideoSize);
            cameraDialogUtil.setConfig(config);
            return cameraDialogUtil;
        }
    }
}
