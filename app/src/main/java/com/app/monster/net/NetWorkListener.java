package com.app.monster.net;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * Created by liulb1 on 2018/8/7.
 */

public interface NetWorkListener {

    void onNetStart();

    void onNetSuccess(String tabName,List<AVObject> list);

    void onNetFailure(AVException e);

    void onNetError();

    void onNetComplete();

}
