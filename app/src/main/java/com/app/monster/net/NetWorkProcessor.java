package com.app.monster.net;
import android.content.Context;
import android.text.TextUtils;
import com.app.monster.utils.NetworkUtils;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import java.util.List;
/**
 * Created by liulb1 on 2018/8/7.
 */
public class NetWorkProcessor {

    public static NetWorkProcessor getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static NetWorkProcessor instance=new NetWorkProcessor();
    }

    public void getHomeNews(Context context,NetWorkListener mListener){
        getNetWorkData(context,"one", 0, 10, "updatedAt", false, mListener);
    }

    private void getNetWorkData(Context context, final String tabName, int skip, int limit, String orderBy, boolean asc, final NetWorkListener mListener){
        if(NetworkUtils.isConnected(context)&&NetworkUtils.isAvailable(context)) {
            AVQuery<AVObject> query = new AVQuery<>(tabName);
            query.skip(skip);
            query.limit(limit);
            if (!TextUtils.isEmpty(orderBy)) {
                if (asc) {
                    query.orderByAscending(orderBy);
                } else {
                    query.orderByDescending(orderBy);
                }
            }
            mListener.onNetStart();
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (null == e) {//收到数据
                        mListener.onNetSuccess(tabName,list);
                        mListener.onNetComplete();
                    } else {//异常
                        mListener.onNetFailure(e);
                    }
                }
            });
        }else{
            mListener.onNetError();
        }
    }

}
