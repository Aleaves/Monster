package com.app.monster.ui.adapter;

import android.support.annotation.Nullable;

import com.app.monster.R;
import com.app.monster.ui.adapter.viewholder.MyViewHolder;
import com.avos.avoscloud.AVObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liulb1 on 2018/8/8.
 */

public class HomePageAdapter extends BaseQuickAdapter<AVObject,MyViewHolder> {

    public HomePageAdapter(int layoutResId, @Nullable List<AVObject> data) {
        super(layoutResId, data);
    }

    public void refreshData(List<AVObject> list){
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(MyViewHolder helper, AVObject item) {
        helper.setText(R.id.home_title_item_tv,item.getString("article_title"))
                .setText(R.id.home_author_item_tv,item.getString("article_author"))
                .setText(R.id.home_time_item_tv,DateToString(item.getDate("createdAt")))
                .setText(R.id.home_content_item_tv,item.getString("article_content"))
                .addOnClickListener(R.id.news_item_ll);
        try {
            String url = item.getJSONObject("article_image").getString("url");
            helper.setImageUrl(R.id.home_image_item_iv,mContext,url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String DateToString(Date date){
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
