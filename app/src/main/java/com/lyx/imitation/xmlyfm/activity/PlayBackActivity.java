package com.lyx.imitation.xmlyfm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lyx.imitation.xmlyfm.R;

/**
 * Created by Administrator on 2016/11/21.
 */

public class PlayBackActivity extends AppCompatActivity {

    public static void startActivity(Activity activity, int albumId, String title, int position) {
        Intent intent = new Intent(activity, PlayBackActivity.class);
        intent.putExtra("albumId", albumId);
        intent.putExtra("title", title);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_back);
    }
}
