package com.app.monster.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.app.monster.R;
import com.app.monster.utils.LaunchUtils;

/**
 * Created by liulb1 on 2018/7/26.
 */

public class SplashActivity extends AppCompatActivity{

    private ImageView mSplashView;
    private ObjectAnimator mAnimator;

    private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            goToNext();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashView = findViewById(R.id.splash_iv);
        startAnimation();
    }

    private void goToNext(){
        LaunchUtils.launch(this,WelcomeActivity.class);
        finish();
    }

    private void startAnimation(){
        mAnimator= ObjectAnimator.ofFloat(mSplashView,"alpha",0,1);
        mAnimator.setDuration(2000);
        mAnimator.start();
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mHandler.sendEmptyMessageDelayed(1,500);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(null!=mAnimator&&mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if(mHandler.hasMessages(1)){
            mHandler.removeMessages(1);
        }
        super.onBackPressed();
    }
}
