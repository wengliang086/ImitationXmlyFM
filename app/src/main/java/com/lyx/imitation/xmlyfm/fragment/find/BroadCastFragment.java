package com.lyx.imitation.xmlyfm.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.fragment.BaseFragment;
import com.lyx.imitation.xmlyfm.model.BroadcastModel;

/**
 * Created by Administrator on 2016/11/8.
 */

public class BroadCastFragment extends BaseFragment {

    private View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_find_broadcast, container, false);
        setDatas();
        return baseView;
    }

    private void setDatas() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, "http://live.ximalaya.com/live-web/v4/homepage", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                BroadcastModel model = gson.fromJson(result, BroadcastModel.class);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
