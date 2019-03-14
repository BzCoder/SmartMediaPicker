package me.bzcoder.mediapicker.photopicker;

import android.app.Activity;
import android.widget.Toast;

import com.cztv.compnent.commoncamera.R;
import me.bzcoder.mediapicker.glide.Glide4Engine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;

import java.util.Arrays;
import java.util.List;

import static me.bzcoder.mediapicker.camera.Constant.REQUEST_CODE_CHOOSE;


/**
 * 照片选择器
 *
 * @author : BaoZhou
 * @date : 2018/7/12 10:02
 */
public class PhotoPickUtils {

    public static final List<String> IMAGE_TYPE = Arrays.asList("jpg", "png", "gif", "bmp", "webp", "jpeg");
    public static final List<String> VIDEO_TYPE = Arrays.asList("mov", "mp4", "3gp", "mpg", "avi", "rmvb");


    static public void getAllSelector(Activity activity, int maxImageSelectable, int maxVideoSelectable) {
        boolean b = PermissionUtils.checkCameraPermission(activity);
        if (b) {
            Matisse.from(activity)
                    .choose(maxVideoSelectable == 0 ? MimeType.ofImage() : MimeType.ofAll())
                    .theme(R.style.Matisse_Zhihu)
                    .countable(false)
                    .addFilter(new FileSizeFilter(320, 320, 10 * Filter.K * Filter.K, 5 * Filter.K * Filter.K, VideoConst.VIDEO_LENGTH))
                    .maxSelectablePerMediaType(maxImageSelectable, 1)
                    .originalEnable(false)
                    .maxOriginalSize(10)
                    .imageEngine(new Glide4Engine())
                    .forResult(REQUEST_CODE_CHOOSE);
        } else {
            Toast.makeText(activity, R.string.permission_request_denied, Toast.LENGTH_LONG)
                    .show();
        }
    }

}


