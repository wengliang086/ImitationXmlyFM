package com.lyx.imitation.xmlyfm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.adapter.AlbumAdapter;
import com.lyx.imitation.xmlyfm.model.AlbumJup;
import com.lyx.imitation.xmlyfm.util.T;

/**
 * Created by Administrator on 2016/11/21.
 * 专辑
 */

public class AlbumActivity extends AppCompatActivity {

    private AlbumAdapter mAdapter;
    private ListView mListView;

    public static void startActivity(Activity activity, int albumId, String title, int position) {
        Intent intent = new Intent(activity, AlbumActivity.class);
        intent.putExtra("albumId", albumId);
        intent.putExtra("title", title);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initView();
        Intent intent = getIntent();
        int albumId = intent.getIntExtra("albumId", 0);
        String title = intent.getStringExtra("title");
        int position = intent.getIntExtra("position", 0);
        setDatas(albumId, title, position);
    }

    private void setDatas(int albumId, String title, int position) {
        int pageSize = 50;
        String url = "http://mobile.ximalaya.com/mobile/v1/album?albumId=" + albumId +
                "&device=android&isAsc=true&pageId=1&pageSize=" + pageSize +
                "&pre_page=0&source=4&statEvent=pageview%2Falbum%40" + albumId +
                "&statModule=" + title + "&statPage=tab%40%E5%8F%91%E7%8E%B0_%E6%8E%A8%E8%8D%90&statPosition=" + position;
        new HttpUtils().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                if (TextUtils.isEmpty(s)) {
                    T.show(AlbumActivity.this, "网络异常！");
                    return;
                }
                AlbumJup albumJup = new Gson().fromJson(s, AlbumJup.class);
                if (albumJup.data.tracks.list == null) {
                    Toast.makeText(AlbumActivity.this, "本书为付费书,请先登录,充值后再来收听", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter = new AlbumAdapter(AlbumActivity.this, albumJup);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_album_listView);
    }
}
