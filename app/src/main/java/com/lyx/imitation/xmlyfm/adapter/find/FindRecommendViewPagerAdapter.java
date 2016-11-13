package com.lyx.imitation.xmlyfm.adapter.find;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lyx.imitation.xmlyfm.model.Recommend;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class FindRecommendViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Recommend.News> list;

    public FindRecommendViewPagerAdapter(Context context, List<Recommend.News> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        position = position % list.size();
        MyImageLoader.load(list.get(position).pic, imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
//        return list.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
