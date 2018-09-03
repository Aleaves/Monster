package com.app.monster.ui.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.monster.R;
import com.app.monster.utils.GlideApp;
import com.app.monster.utils.GlideUtils;
import com.avos.avoscloud.AVObject;
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;

/**
 * Created by liulb1 on 2018/8/8.
 */

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder>{

    private Context mContext;
    private List<AVObject> lists;

    public HomePageAdapter(Context mContext,List<AVObject> lists){
        this.mContext = mContext;
        if(null==lists){
            this.lists = new ArrayList<>();
        }else {
            this.lists = lists;
        }
    }

    public void refreshData(List<AVObject> lists){
        this.lists.clear();
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        AVObject avObject = lists.get(position);
        holder.mTitle.setText(avObject.getString("article_title"));
        holder.mAuthor.setText(avObject.getString("article_author"));
        holder.mContent.setText(avObject.getString("article_content"));
        holder.mTime.setText(avObject.getDate("createdAt").toString());
        try {
            String url = avObject.getJSONObject("article_image").getString("url");
            GlideUtils.getInstance().displayImage(mContext,url,holder.mImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mAuthor;
        TextView mTime;
        TextView mContent;
        ImageView mImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.home_title_item_tv);
            mAuthor = itemView.findViewById(R.id.home_author_item_tv);
            mTime = itemView.findViewById(R.id.home_time_item_tv);
            mImage = itemView.findViewById(R.id.home_image_item_iv);
            mContent = itemView.findViewById(R.id.home_content_item_tv);
        }
    }

}
