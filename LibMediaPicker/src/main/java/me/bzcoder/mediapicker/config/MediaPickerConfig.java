package me.bzcoder.mediapicker.config;

import com.zhihu.matisse.MimeType;

import java.util.Set;

import me.bzcoder.mediapicker.cameralibrary.JCameraView;

public class MediaPickerConfig {
    private boolean countable;
    private boolean originalEnable;
    private int maxOriginalSize;
    private int maxImageSelectable;
    private int maxVideoSelectable;
    private int maxWidth;
    private int maxHeight;

    /**
     * 单位：MB , 默认15MB
     */
    private int maxImageSize ;

    /**
     * 单位：毫秒 ,默认20秒
     */
    private int maxVideoLength;
    /**
     * 单位：MB
     */
    private int maxVideoSize;

    public MediaPickerConfig() {

    }


    public boolean isCountable() {
        return countable;
    }

    public void setCountable(boolean countable) {
        this.countable = countable;
    }

    public boolean isOriginalEnable() {
        return originalEnable;
    }

    public void setOriginalEnable(boolean originalEnable) {
        this.originalEnable = originalEnable;
    }

    public int getMaxImageSelectable() {
        return maxImageSelectable;
    }

    public void setMaxImageSelectable(int maxImageSelectable) {
        this.maxImageSelectable = maxImageSelectable;
    }

    public int getMaxVideoSelectable() {
        return maxVideoSelectable;
    }

    public void setMaxVideoSelectable(int maxVideoSelectable) {
        this.maxVideoSelectable = maxVideoSelectable;
    }

    public int getMaxOriginalSize() {
        return maxOriginalSize;
    }

    public void setMaxOriginalSize(int maxOriginalSize) {
        this.maxOriginalSize = maxOriginalSize;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxImageSize() {
        return maxImageSize;
    }

    public void setMaxImageSize(int maxImageSize) {
        this.maxImageSize = maxImageSize;
    }

    public int getMaxVideoLength() {
        return maxVideoLength;
    }

    public void setMaxVideoLength(int maxVideoLength) {
        this.maxVideoLength = maxVideoLength;
    }

    public int getMaxVideoSize() {
        return maxVideoSize;
    }

    public void setMaxVideoSize(int maxVideoSize) {
        this.maxVideoSize = maxVideoSize;
    }

    public int getCameraMediaType() {
        if (getMaxImageSelectable() == 0 && getMaxVideoSelectable() > 0) {
            return JCameraView.BUTTON_STATE_ONLY_RECORDER;
        } else if (getMaxImageSelectable() > 0 && getMaxVideoSelectable() == 0) {
            return JCameraView.BUTTON_STATE_ONLY_CAPTURE;
        }
        return JCameraView.BUTTON_STATE_BOTH;
    }

    public Set getPhotoPickerMediaType() {
        if (getMaxImageSelectable() == 0 && getMaxVideoSelectable() > 0) {
            return MimeType.ofVideo();
        } else if (getMaxImageSelectable() > 0 && getMaxVideoSelectable() == 0) {
            return MimeType.ofImage();
        }
        return MimeType.ofAll();
    }
}
