package com.app.monster.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.monster.R;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

/**
 * Created by liulb1 on 2018/7/25.
 * 欢迎页
 */

public class WelcomeActivity extends AppIntro2{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setDescription("青山隐隐水迢迢，秋尽江南草未凋。\n\n" +
                "二十四桥明月夜，玉人何处教吹箫。");
        sliderPage1.setBgColor(Color.TRANSPARENT);
        addSlide(AppIntroFragment.newInstance(sliderPage1));
        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setDescription("入我相思门，知我相思苦， \n\n" +
                "长相思兮长相忆，短相思兮无穷极， \n\n" +
                "早知如此绊人心，何如当初莫相识。");
        sliderPage2.setBgColor(Color.TRANSPARENT);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setDescription("人生若只如初见，何事秋风悲画扇。\n\n" +
                "等闲变却故人心，却道故人心易变。\n\n" +
                "骊山语罢清宵半，泪雨零铃终不怨。\n\n" +
                "何如薄幸锦衣郎，比翼连枝当日愿。");
        sliderPage3.setBgColor(Color.TRANSPARENT);
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setDescription("曾经沧海难为水，除却巫山不是云。\n\n" +
                "取次花丛懒回顾，半缘修道半缘君。");
        sliderPage4.setBgColor(Color.TRANSPARENT);
        addSlide(AppIntroFragment.newInstance(sliderPage4));


        // Declare a new image view
        ImageView imageView = new ImageView(this);

        // Bind a drawable to the imageview
        imageView.setImageResource(R.mipmap.app_welcome);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // Set layout params
        imageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Bind the background to the intro
        setBackgroundView(imageView);

        setDepthAnimation();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Log.i("=======","skip");
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Log.i("=======","onDonePressed");
    }

}
