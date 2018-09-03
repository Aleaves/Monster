package com.app.monster.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;

/**
 * Created by liulb1 on 2018/8/31.
 */

public class GlideUtils {

    public static GlideUtils mInstance;

    public static GlideUtils getInstance(){
        if(null==mInstance){
            mInstance = new GlideUtils();
        }
        return mInstance;
    }

    /**
     * 正常加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImage(Context context, String url, ImageView imageView){
        GlideApp.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    /**
     * 图片滤镜
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImageWithSketch(Context context, String url, ImageView imageView){
        GlideApp.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new SketchFilterTransformation())
                .into(imageView);
    }


}
