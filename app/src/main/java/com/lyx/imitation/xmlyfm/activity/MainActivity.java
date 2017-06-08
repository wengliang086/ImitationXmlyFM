package com.lyx.imitation.xmlyfm.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.fragment.DownloadFragment;
import com.lyx.imitation.xmlyfm.fragment.FindFragment;
import com.lyx.imitation.xmlyfm.fragment.MineFragment;
import com.lyx.imitation.xmlyfm.fragment.SubscribeFragment;

/**
 * Created by Administrator on 2016/11/8.
 */

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment showingFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.id_main_rg);
        radioGroup.setOnCheckedChangeListener(this);

        setRadioButtonTopDrawableSize(R.id.id_main_find, R.drawable.selector_main_button_find);
        setRadioButtonTopDrawableSize(R.id.id_main_download, R.drawable.selector_main_button_download);
        setRadioButtonTopDrawableSize(R.id.id_main_subscribe, R.drawable.selector_main_button_subscribe);
        setRadioButtonTopDrawableSize(R.id.id_main_mine, R.drawable.selector_main_button_mine);

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    /**
     * 解决不能控制RadioButton图片大小的问题
     *
     * @param id
     * @param drawableId
     */
    private void setRadioButtonTopDrawableSize(int id, int drawableId) {
        RadioButton radioButton = (RadioButton) findViewById(id);
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, 45, 45);
        radioButton.setCompoundDrawables(null, drawable, null, null);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.id_main_find:
                replaceFragment(FindFragment.class);
                break;
            case R.id.id_main_download:
                replaceFragment(DownloadFragment.class);
                break;
            case R.id.id_main_subscribe:
                replaceFragment(SubscribeFragment.class);
                break;
            case R.id.id_main_mine:
                replaceFragment(MineFragment.class);
                break;
        }
    }

    private void replaceFragment(Class<? extends Fragment> fragmentClass) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (showingFragment != null) {
            transaction.hide(showingFragment);
        }
        showingFragment = fragmentManager.findFragmentByTag(fragmentClass.getName());
        if (showingFragment == null) {
            try {
                showingFragment = fragmentClass.getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            transaction.add(R.id.id_main_frame_layout, showingFragment, fragmentClass.getName());
        } else {
            transaction.show(showingFragment);
        }
        transaction.commit();
    }
}
