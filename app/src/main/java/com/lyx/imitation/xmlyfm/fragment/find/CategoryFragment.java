package com.lyx.imitation.xmlyfm.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.adapter.find.CategoryAdapter;
import com.lyx.imitation.xmlyfm.fragment.BaseFragment;
import com.lyx.imitation.xmlyfm.model.Category;
import com.lyx.imitation.xmlyfm.util.T;

/**
 * Created by Administrator on 2016/11/8.
 */

public class CategoryFragment extends BaseFragment {

    private View baseView;
    private ListView listView;
    private Category category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_find_category, container, false);
        initView();
        setDatas();
        return baseView;
    }

    private void initView() {
        listView = (ListView) baseView.findViewById(R.id.id_fragment_category_listview);
    }

    private void setDatas() {
        String url = "http://mobile.ximalaya.com/mobile/discovery/v1/categories?device=android&picVersion=11&scale=2";
        new HttpUtils().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                if (TextUtils.isEmpty(s)) {
                    T.show(getActivity(), "网络异常！");
                    return;
                }
                category = new Gson().fromJson(s, Category.class);
                listView.setAdapter(new CategoryAdapter(getActivity(), category));
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
