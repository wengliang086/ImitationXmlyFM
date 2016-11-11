package com.lyx.imitation.xmlyfm.fragment.subscribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyx.imitation.xmlyfm.R;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SubscribeSubscribeFragment extends Fragment {

    private View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_subscribe_subscribe, container, false);
        initView();
        return baseView;
    }

    private void initView() {

    }
}
