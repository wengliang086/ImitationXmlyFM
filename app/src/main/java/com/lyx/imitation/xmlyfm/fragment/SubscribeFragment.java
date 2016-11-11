package com.lyx.imitation.xmlyfm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.adapter.TabLayoutFragmentPagerAdapter;
import com.lyx.imitation.xmlyfm.fragment.subscribe.SubscribeHistoryFragment;
import com.lyx.imitation.xmlyfm.fragment.subscribe.SubscribeRecommendFragment;
import com.lyx.imitation.xmlyfm.fragment.subscribe.SubscribeSubscribeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SubscribeFragment extends BaseFragment {

    private List<String> titles = Arrays.asList("推荐", "订阅", "历史");
    private List<Fragment> fragments = new ArrayList<>(titles.size());
    private View baseView;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_subscribe, container, false);
        initView();
        return baseView;
    }

    private void initView() {
        viewPager = (ViewPager) baseView.findViewById(R.id.id_subscribe_viewPager);
        tabLayout = (TabLayout) baseView.findViewById(R.id.id_subscribe_tabLayout);
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        fragments.add(new SubscribeRecommendFragment());
        fragments.add(new SubscribeSubscribeFragment());
        fragments.add(new SubscribeHistoryFragment());
        viewPager.setAdapter(new TabLayoutFragmentPagerAdapter(getFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
    }
}
