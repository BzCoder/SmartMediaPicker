package me.bzcoder.mediapicker.photopicker;

import android.Manifest;
import android.app.Activity;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.bzcoder.mediapicker.R;
import me.bzcoder.mediapicker.config.Constant;
import me.bzcoder.mediapicker.config.MediaPickerConfig;
import me.bzcoder.mediapicker.glide.Glide4Engine;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.filter.Filter;


/**
 * 照片选择器
 *
 * @author : BaoZhou
 * @date : 2018/7/12 10:02
 */
public class PhotoPickUtils {
    static public void getAllSelector(final Activity activity, final MediaPickerConfig config) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(Boolean aBoolean) {
                Matisse.from(activity)
                        .choose(config.getPhotoPickerMediaType())
                        .theme(R.style.Matisse_Zhihu)
                        .countable(config.isCountable())
                        .addFilter(new FileSizeFilter(config.getMaxWidth(), config.getMaxHeight(), config.getMaxVideoSize() * Filter.K * Filter.K, config.getMaxImageSize() * Filter.K * Filter.K, config.getMaxVideoLength()))
                        .maxSelectablePerMediaType(config.getMaxImageSelectable()==0?1:config.getMaxImageSelectable()
                                , config.getMaxVideoSelectable()==0?1:config.getMaxVideoSelectable())
                        .originalEnable(config.isOriginalEnable())
                        .maxOriginalSize(config.getMaxOriginalSize())
                        .imageEngine(new Glide4Engine())
                        .forResult(Constant.REQUEST_CODE_CHOOSE);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, R.string.permission_request_denied, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onComplete() {

            }
        });







    }

}


