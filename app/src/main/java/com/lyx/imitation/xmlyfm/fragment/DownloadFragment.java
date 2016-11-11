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
import com.lyx.imitation.xmlyfm.fragment.download.DownloadAlbumFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class DownloadFragment extends BaseFragment {

    private List<String> titles = Arrays.asList("专辑", "声音", "下载中");
    private List<Fragment> fragments = new ArrayList<>(titles.size());
    private View baseView;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_download, container, false);
        initView();
        return baseView;
    }

    private void initView() {
        viewPager = (ViewPager) baseView.findViewById(R.id.id_download_viewPager);
        tabLayout = (TabLayout) baseView.findViewById(R.id.id_download_tabLayout);
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        fragments.add(new DownloadAlbumFragment());
        fragments.add(new DownloadAlbumFragment());
        fragments.add(new DownloadAlbumFragment());
        viewPager.setAdapter(new TabLayoutFragmentPagerAdapter(getFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
    }
}
