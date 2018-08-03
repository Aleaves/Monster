package com.app.monster.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liulb1 on 2018/7/30.
 */

public abstract class BaseActivity extends AppCompatActivity{

    Unbinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeSetContentView();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        initView();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void beforeSetContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }
}
