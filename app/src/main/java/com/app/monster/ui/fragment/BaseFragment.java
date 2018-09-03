package com.app.monster.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.monster.net.NetWorkListener;
import com.app.monster.ui.activity.BaseActivity;
import com.avos.avoscloud.AVException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liulb1 on 2018/7/30.
 */

public abstract class BaseFragment extends Fragment implements NetWorkListener{
    Unbinder mBinder;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        mBinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            initViews();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    public abstract int getLayoutId();

    public abstract void initViews();

    /**
     * 显示loading
     */
    protected void showLoading(){
        if(null!=mActivity){
            mActivity.showLoading();
        }
    }

    /**
     * 取消loading
     */
    protected void dismissLoading(){
        if(null!=mActivity){
            mActivity.dismissLoading();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    @Override
    public void onNetStart() {
        showLoading();
    }

    @Override
    public void onNetError() {
        //提示网络连接失败
    }

    @Override
    public void onNetComplete() {
        dismissLoading();
    }

    @Override
    public void onNetFailure(AVException e) {
        //失败提示
    }
}
