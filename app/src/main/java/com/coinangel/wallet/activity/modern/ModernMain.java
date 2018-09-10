package com.coinangel.wallet.activity.modern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.coinangel.wallet.R;

/**
 * Created by lianzhi on 2018/9/10.
 */

public class ModernMain extends AppCompatActivity {


    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);

        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(false);
        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.action_bar_logo);

        getWindow().setBackgroundDrawableResource(R.drawable.background_main);

    }
}
