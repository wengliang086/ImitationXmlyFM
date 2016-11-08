package com.lyx.imitation.xmlyfm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.model.Welcome;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;

/**
 * Created by Administrator on 2016/11/7.
 */

public class WelcomeActivity extends AppCompatActivity {

    private String url = "http://adse.ximalaya.com/ting/loading?androidId=e025fc53f2a025d9c5013367462c809338b23c7c&appid=0&device=android&name=loading&network=wifi&operator=0&userAgent=Mozilla/5.0%20(Linux;%20Android%204.4.2;%20NoxW%20Build/KOT49H)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Version/4.0%20Chrome/30.0.0.0%20Mobile%20Safari/537.36&version=5.4.21";
    private ImageView imageView;
    private Button skipBtn;
    private int count = 3;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                startActivity(MainActivity.class);
            } else if (msg.what == 1) {
                skipBtn.setText("跳过 " + count);
                count--;
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    private void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imageView = (ImageView) findViewById(R.id.id_welcome_image_view);
        skipBtn = (Button) findViewById(R.id.id_sdkp_btn);
        skipBtn.setVisibility(View.GONE);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                final Welcome welcome = new Gson().fromJson(responseInfo.result, Welcome.class);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyImageLoader.load(welcome.data.get(0).cover, imageView);
                skipBtn.setVisibility(View.VISIBLE);
                handler.sendEmptyMessage(1);

                skipBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.removeMessages(0);
                        startActivity(MainActivity.class);
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.removeMessages(0);

                        Intent intent = new Intent(WelcomeActivity.this, WelcomeWebActivity.class);
                        intent.putExtra("url", welcome.data.get(0).link);
                        startActivity(intent);
                        handler.removeMessages(0);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

        handler.sendEmptyMessageDelayed(0, 4000);
    }
}
