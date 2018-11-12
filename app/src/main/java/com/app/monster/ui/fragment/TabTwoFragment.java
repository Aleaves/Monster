package com.app.monster.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.monster.R;
import com.app.monster.ui.activity.DataBindingTestActivity;
import com.avos.avoscloud.AVObject;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by liulb1 on 2018/7/27.
 */

public class TabTwoFragment extends BaseFragment{


    @Override
    public void onNetSuccess(String tabName, List<AVObject> list) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab_two;
    }

    @Override
    public void initViews() {

    }

    @OnClick(R.id.bt_databinding)
    public void goToNext(){
        Intent intent = new Intent(getActivity(), DataBindingTestActivity.class);
        startActivity(intent);
    }

}
