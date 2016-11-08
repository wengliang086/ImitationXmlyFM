package com.lyx.imitation.xmlyfm.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lyx.imitation.xmlyfm.R;

/**
 * Created by Administrator on 2016/11/8.
 */

public class WelcomeWebActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_web);

        url = getIntent().getStringExtra("url");
        initView();
    }

    private void initView() {
        WebView webView = (WebView) findViewById(R.id.id_welcome_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("gb2312");
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); // Android默认的处理方式
                handler.proceed();  // 接受所有网站的证书
                //handleMessage(Message msg); // 进行其他处理
            }
        });

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("加载中....");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    WelcomeWebActivity.this.setTitle("加载完成");
                    pd.dismiss();
                } else {
                    WelcomeWebActivity.this.setTitle("加载中.......");
                    pd.show();
                }
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            startActivity(new Intent(WelcomeWebActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
