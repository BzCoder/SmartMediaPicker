/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.bzcoder.mediapicker.photopicker;

import android.content.Context;

import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : BaoZhou
 * @date : 2018/7/18 20:05
 */
public class FileSizeFilter extends Filter {

    private int mMinWidth;
    private int mMinHeight;
    private int mMaxSize;
    private int mMaxVideoLength;
    private int mMaxImageSize;

    public FileSizeFilter(int minWidth, int minHeight, int maxVideoSizeInBytes, int maxImageSize, int maxVideoLength) {
        mMinWidth = minWidth;
        mMinHeight = minHeight;
        mMaxSize = maxVideoSizeInBytes;
        mMaxVideoLength = maxVideoLength;
        mMaxImageSize = maxImageSize;
    }

    @Override
    public Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {{
            add(MimeType.GIF);
        }};
    }

    @Override
    public IncapableCause filter(Context context, Item item) {
//        if (!needFiltering(context, item))
//            return null;
        if (item.duration > mMaxVideoLength) {
            return new IncapableCause(IncapableCause.DIALOG, "视频长度不要超过10秒");

        } else if (item.duration == 0) {
//            Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
            if (item.size > mMaxImageSize) {
                return new IncapableCause(IncapableCause.DIALOG, "文件大小不要超过" + mMaxImageSize / Filter.K / Filter.K + "M");
            }
        }

        return null;
    }

}
