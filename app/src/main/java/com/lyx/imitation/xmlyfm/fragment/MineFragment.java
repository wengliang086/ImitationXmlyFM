package com.lyx.imitation.xmlyfm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.activity.RegisterActivity;

/**
 * Created by Administrator on 2016/11/8.
 */

public class MineFragment extends BaseFragment {

    private View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        return baseView;
    }

    private void initView() {
        baseView.findViewById(R.id.id_mine_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), RegisterActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                // 实现淡入浅出的效果
//                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                // 由左向右滑入的效果
//                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                // 实现zoommin 和 zoomout (自定义的动画)
//                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
            }
        });
    }
}
