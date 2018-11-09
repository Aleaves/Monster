package com.app.monster.ui.adapter.viewholder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;

import com.app.monster.utils.GlideUtils;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liulb1 on 2018/11/8.
 */

public class MyViewHolder extends BaseViewHolder{

    public MyViewHolder(View view) {
        super(view);
    }

    public MyViewHolder setImageUrl(@IdRes int viewId, Context context,String url){
        ImageView view = getView(viewId);
        GlideUtils.getInstance().displayImage(context,url,view);
        return this;
    }

}
