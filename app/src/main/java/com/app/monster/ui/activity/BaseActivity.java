package com.app.monster.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.app.monster.view.LoadingDailog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liulb1 on 2018/7/30.
 */

public abstract class BaseActivity extends AppCompatActivity{

    Unbinder mBinder;
    protected LoadingDailog mLoadingDailog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeSetContentView();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        initDialog();
        initView();
    }

    /**
     * 初始化loading
     */
    private void initDialog(){
        LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(false);
        mLoadingDailog = loadBuilder.create();
    }

    /**
     * 显示loading
     */
    public void showLoading(){
        if(null!=mLoadingDailog){
            mLoadingDailog.show();
        }
    }

    /**
     * 取消loading
     */
    public void dismissLoading(){
        if(null!=mLoadingDailog){
            mLoadingDailog.dismiss();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public void beforeSetContentView(){
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }
}
