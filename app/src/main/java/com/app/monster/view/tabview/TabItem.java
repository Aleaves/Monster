package com.app.monster.view.tabview;

import android.support.v4.app.Fragment;

/**
 * Created by yx on 16/4/3.
 */
public class TabItem {

    /**
     * icon
     */
    public int imageResId;
    /**
     * 文本
     */
    public int lableResId;


    public Class<? extends Fragment>tagFragmentClz;

    public TabItem(int imageResId, int lableResId) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
    }


    public TabItem(int imageResId, int lableResId, Class<? extends Fragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
    }
}



