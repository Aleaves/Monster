package com.app.monster.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;

import com.app.monster.ui.activity.WelcomeActivity;

/**
 * Created by liulb1 on 2018/7/26.
 */

public class LaunchUtils {

    public static void launch(Activity activity,Class clazz){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(new Intent(activity,clazz), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        }else{
            activity.startActivity(new Intent(activity,clazz));
        }
    }

}
