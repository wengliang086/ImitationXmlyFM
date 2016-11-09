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
import com.lyx.imitation.xmlyfm.adapter.FindFragmentAdapter;
import com.lyx.imitation.xmlyfm.fragment.find.AnchorFragment;
import com.lyx.imitation.xmlyfm.fragment.find.BroadCastFragment;
import com.lyx.imitation.xmlyfm.fragment.find.CategoryFragment;
import com.lyx.imitation.xmlyfm.fragment.find.RecommendFragment;
import com.lyx.imitation.xmlyfm.fragment.find.TopListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class FindFragment extends BaseFragment {

    private List<String> titles = Arrays.asList("推荐", "分类", "广播", "榜单", "主播");
    private List<Fragment> fragments = new ArrayList<>();
    private View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_find, container, false);
        initView();
        return baseView;
    }

    private void initView() {
        TabLayout tabLayout = (TabLayout) baseView.findViewById(R.id.id_find_tabLayout);
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        fragments.add(new RecommendFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new BroadCastFragment());
        fragments.add(new TopListFragment());
        fragments.add(new AnchorFragment());
        ViewPager viewPager = (ViewPager) baseView.findViewById(R.id.id_find_viewPager);
        viewPager.setAdapter(new FindFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
    }
}
