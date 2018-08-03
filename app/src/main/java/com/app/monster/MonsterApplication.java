package com.app.monster;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by liulb1 on 2018/7/31.
 */

public class MonsterApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"C6ScxA78oy3OPA9hLA4KKOxd-gzGzoHsz","zjcVKrnxcIuRnMzUX52MlfwW");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
