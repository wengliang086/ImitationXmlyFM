package com.lyx.imitation.xmlyfm;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/11/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoder(this);
    }

    private void initImageLoder(Context context) {
        // 设置缓存图片的路径
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                // 设置当前线程的优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // 缓存显示不同大小的同一张图片
                .denyCacheImageMultipleSizesInMemory()
                // .discCacheFileNameGenerator(new
                // Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .memoryCache(new WeakMemoryCache())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        /*
		 * getInstance单例模式：在内存中只能存在一个对象 构造方法私有
		 */
        ImageLoader.getInstance().init(configuration);
    }
}
