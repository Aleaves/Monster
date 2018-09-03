package com.app.monster.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.monster.R;
import com.app.monster.net.NetWorkProcessor;
import com.app.monster.ui.adapter.HomePageAdapter;
import com.avos.avoscloud.AVObject;
import java.util.List;
import butterknife.BindView;

/**
 * Created by liulb1 on 2018/7/27.
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private HomePageAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new HomePageAdapter(mActivity,null);
        mRecyclerView.setAdapter(mAdapter);
        NetWorkProcessor.getInstance().getHomeNews(mActivity,this);
    }

    @Override
    public void onNetSuccess(String tabName, List<AVObject> list) {
        mAdapter.refreshData(list);
    }

}
