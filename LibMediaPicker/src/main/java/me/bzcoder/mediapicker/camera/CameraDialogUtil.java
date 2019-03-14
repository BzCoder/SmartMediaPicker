package me.bzcoder.mediapicker.camera;

import android.support.v4.app.FragmentManager;

public class CameraDialogUtil {
    CameraDialogFragment cameraDialogFragment;

    public static void showCameraDialog(FragmentManager manager) {
        CameraDialogFragment cameraDialogFragment = new CameraDialogFragment();
        cameraDialogFragment.show(manager, "cameraDialogFragment");
    }

}
