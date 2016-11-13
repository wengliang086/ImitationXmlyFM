package com.lyx.imitation.xmlyfm.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.adapter.find.FindRecommendAdapter;
import com.lyx.imitation.xmlyfm.adapter.find.FindRecommendViewPagerAdapter;
import com.lyx.imitation.xmlyfm.fragment.BaseFragment;
import com.lyx.imitation.xmlyfm.model.Recommend;
import com.lyx.imitation.xmlyfm.task.base.TaskCallback;
import com.lyx.imitation.xmlyfm.task.base.TaskResult;
import com.lyx.imitation.xmlyfm.util.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class RecommendFragment extends BaseFragment implements TaskCallback {

    private View baseView;
    private ListView listView;
    private ProgressBar progressBar;
    private FindRecommendAdapter findRecommendAdapter;
    private ViewPager headerViewPager, footerViewPager;
    // 填充数据
    private Recommend recommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_find_recommend, container, false);
        initView();
        setDatas();
        return baseView;
    }

    private void initView() {
        progressBar = (ProgressBar) baseView.findViewById(R.id.id_recommend_progressBar);
        listView = (ListView) baseView.findViewById(R.id.id_recommend_listView);
        addHeaderView();
        addFooterView();
        findRecommendAdapter = new FindRecommendAdapter(getActivity(), recommend);
        listView.setAdapter(findRecommendAdapter);
    }

    private void setDatas() {
        String url = "http://mobile.ximalaya.com/mobile/discovery/v1/recommends?channel=yz-xm&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.3.20.14";
        new HttpUtils().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                if (TextUtils.isEmpty(s)) {
                    T.show(getActivity(), "网络异常！");
                    return;
                }
                recommend = new Gson().fromJson(s, Recommend.class);
                findRecommendAdapter.setRecommend(recommend);
                // 轮播图片
                headerViewPager.setAdapter(new FindRecommendViewPagerAdapter(getActivity(), recommend.focusImages.list));
                setVPDoc(recommend.focusImages.list);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onTaskFinished(TaskResult result) {

    }

    private void addHeaderView() {
        View view = View.inflate(getActivity(), R.layout.fragment_find_recommend_header, null);
        headerViewPager = (ViewPager) view.findViewById(R.id.id_recommend_head_viewPager);
        headerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                swichDoc(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        llDoc = (LinearLayout) view.findViewById(R.id.id_recommend_head_linearLayout);
        listView.addHeaderView(view);
    }

    //存放vp的小圆点
    private List<ImageView> vpDoc = new ArrayList<>();
    private LinearLayout llDoc;

    /**
     * 设置ViewPager小圆点
     * @param list
     */
    private void setVPDoc(List<Recommend.News> list) {
        vpDoc.clear();
        for (Recommend.News n : list) {
            ImageView ivDoc = new ImageView(getActivity());
            llDoc.addView(ivDoc);
            /*
             * 1、通过控件获取到设置属性的params对象
			 * 2、给params添加属性
			 * 3、把设置好的属性对象交给我们的控件
			 */
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivDoc.getLayoutParams();
            params.width = 10;
            params.height = 10;
            params.setMargins(0, 0, 15, 0);
            ivDoc.setLayoutParams(params);
            ivDoc.setImageResource(R.drawable.shap_docll);
            vpDoc.add(ivDoc);
        }
        // 给小圆点设置一个初值颜色
        vpDoc.get(0).setImageResource(R.drawable.shap_docll_end);
    }

    /**
     * 根据position切换圆点的颜色
     * @param position
     */
    private void swichDoc(int position) {
        for (int i = 0; i < vpDoc.size(); i++) {
            //找到position图片的位置，设置红色
            if (position % vpDoc.size() == i) {
                vpDoc.get(i).setImageResource(R.drawable.shap_docll_end);
            } else {
                vpDoc.get(i).setImageResource(R.drawable.shap_docll);
            }
        }
    }

    private void addFooterView() {
        View view = View.inflate(getActivity(), R.layout.fragment_find_recommend_footer, null);
        listView.addFooterView(view);
    }
}
