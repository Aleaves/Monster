package com.app.monster.ui.fragment;
import android.util.Log;
import android.widget.TextView;
import com.app.monster.R;
import com.app.monster.view.LoadingDailog;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.List;

import butterknife.BindView;

/**
 * Created by liulb1 on 2018/7/27.
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.tv)
    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {

        LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(getActivity())
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(false);
        LoadingDailog dialog=loadBuilder.create();
        dialog.show();
        AVQuery<AVObject> query = new AVQuery<>("Memory");
        query.limit(10);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (int i=0;i<list.size();i++){
                    Log.i("=======1",list.get(i).toString());
                }
            }
        });
    }

}
