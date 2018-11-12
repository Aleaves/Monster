package com.app.monster.view.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.monster.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class PtrUmsDefaultHeader extends FrameLayout implements PtrUIHandler{

    private int mRotateAniTime = 150;

    private ImageView mPullView;
    private ImageView mRefreshView;

    private AnimationDrawable mPullAnim;
    private AnimationDrawable mRefreshAnim;


    public PtrUmsDefaultHeader(Context context) {
        super(context);
        initViews(null);
    }

    public PtrUmsDefaultHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public PtrUmsDefaultHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, in.srain.cube.views.ptr.R.styleable.PtrClassicHeader, 0, 0);
        if (arr != null) {
            mRotateAniTime = arr.getInt(in.srain.cube.views.ptr.R.styleable.PtrClassicHeader_ptr_rotate_ani_time, mRotateAniTime);
        }

        View header = LayoutInflater.from(getContext()).inflate(R.layout.cube_ptr_ums_default_header, this);

        mPullView = header.findViewById(R.id.ptr_pull_view);

        mRefreshView = header.findViewById(R.id.ptr_refresh_view);

        mPullAnim = (AnimationDrawable) mPullView.getBackground();

        mRefreshAnim = (AnimationDrawable) mRefreshView.getBackground();

        resetView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setRotateAniTime(int time) {
        if (time == mRotateAniTime || time == 0) {
            return;
        }
        mRotateAniTime = time;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
//        mLastUpdateTimeKey = key;
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        setLastUpdateTimeKey(object.getClass().getName());
    }


    private void resetView() {

        if(mPullAnim!=null&&mPullAnim.isRunning()){
            mPullAnim.stop();
        }
        if(mRefreshAnim!=null&&mRefreshAnim.isRunning()){
            mRefreshAnim.stop();
        }

        mPullView.setVisibility(GONE);
        mRefreshView.setVisibility(GONE);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mPullView.setVisibility(View.VISIBLE);
        if(mPullAnim!=null){
            mPullAnim.start();
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mPullView.setVisibility(GONE);
        mRefreshView.setVisibility(VISIBLE);
        if(mPullAnim!=null&&mPullAnim.isRunning()){
            mPullAnim.stop();
        }
        if(mRefreshAnim!=null){
            mRefreshAnim.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mRefreshView.setVisibility(GONE);
        if(mRefreshAnim!=null&&mRefreshAnim.isRunning()){
            mRefreshAnim.stop();
        }
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromBottomUnderTouch(frame);
//                if (mRotateView != null) {
//                    mRotateView.clearAnimation();
//                    mRotateView.startAnimation(mReverseFlipAnimation);
//                }
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromTopUnderTouch(frame);
//                if (mRotateView != null) {
//                    mRotateView.clearAnimation();
//                    mRotateView.startAnimation(mFlipAnimation);
//                }
            }
        }
    }
}
