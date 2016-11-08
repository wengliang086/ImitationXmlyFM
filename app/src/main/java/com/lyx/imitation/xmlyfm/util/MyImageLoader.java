package com.lyx.imitation.xmlyfm.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lyx.imitation.xmlyfm.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * ImageLoder的工具类 1、设置了加载图片的一些规则 2、设置是否使用缓存
 */
public class MyImageLoader {

    public static void load(String url, ImageView image) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                // 图片质量
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 图片加载好后渐入的动画时间
                // .displayer(new FadeInBitmapDisplayer(1000))//
                // 设置成圆角图片
                // .displayer(new RoundedBitmapDisplayer(20))
                .build();
        // 加载图片
        ImageLoader.getInstance().displayImage(url, image, options);
    }
}
