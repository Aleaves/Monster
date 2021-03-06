package com.app.monster.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.app.monster.R;
import com.app.monster.net.NetWorkProcessor;
import com.app.monster.ui.activity.WebActivity;
import com.app.monster.ui.adapter.HomePageAdapter;
import com.app.monster.utils.LaunchUtils;
import com.app.monster.view.refresh.PtrUmsFrameLayout;
import com.avos.avoscloud.AVObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;
import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by liulb1 on 2018/7/27.
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrUmsFrameLayout ptrFrame;

    private boolean isRefresh = false;

    private HomePageAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new HomePageAdapter(R.layout.adapter_home_news_item,null);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String url = mAdapter.getData().get(position).getString("article_url");
                String title = mAdapter.getData().get(position).getString("article_title");
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                bundle.putString("title",title);
                LaunchUtils.launch(getActivity(), WebActivity.class,bundle);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        getData();
        ptrFrame.setLastUpdateTimeRelateObject(this);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRefresh = true;
                getData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    /**
     * 加载数据
     */
    private void getData(){
        NetWorkProcessor.getInstance().getHomeNews(mActivity,this);
    }

    @Override
    public void onNetStart() {
        if(!isRefresh) {
            super.onNetStart();
        }
    }

    @Override
    public void onNetSuccess(String tabName, List<AVObject> list) {
        mAdapter.refreshData(list);
    }

    @Override
    public void onNetComplete() {
        if(!isRefresh) {
            super.onNetComplete();
        }else{
            ptrFrame.refreshComplete();
        }
    }
}
