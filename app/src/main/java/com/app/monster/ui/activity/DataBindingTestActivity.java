package com.app.monster.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.monster.R;
import com.app.monster.databinding.ActivityDatabindingTestBinding;
import com.app.monster.entity.User;

/**
 * Created by liulb1 on 2018/11/12.
 */

public class DataBindingTestActivity extends AppCompatActivity{

    public ActivityDatabindingTestBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_test);
        mBinding.setUser(new User("大漠野狼"));
    }
}
