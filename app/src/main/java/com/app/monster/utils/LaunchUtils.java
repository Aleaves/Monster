package com.app.monster.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.app.monster.ui.activity.WelcomeActivity;

/**
 * Created by liulb1 on 2018/7/26.
 */

public class LaunchUtils {

    public static void launch(Activity activity,Class clazz){
        activity.startActivity(new Intent(activity,clazz));
    }

    public static void launch(Activity activity, Class clazz, Bundle bundle){
        Intent intent = new Intent(activity,clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

}
