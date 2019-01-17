package cn.intersteller.darkintersteller.custview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class SwipeRefreshLayoutForBanner extends SwipeRefreshLayout {

    private int mScaledTouchSlop;
    private boolean mIsBannerScolling;
    private float startX;
    private float startY;

    public SwipeRefreshLayoutForBanner(@NonNull Context context) {
        super(context);
    }

    public SwipeRefreshLayoutForBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                mIsBannerScolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsBannerScolling) {
                    return false;
                }
                float endX = getX();
                float endY = getY();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
//                Log.i("deng11111111", "distanceX = " + distanceX);
//                Log.i("deng11111111", "mScaledTouchSlop = " + mScaledTouchSlop);
                //试出来的。。。。估计每个手机都不一样
                //TODO
                if (distanceX > 37 * mScaledTouchSlop && distanceX > distanceY) {
                    mIsBannerScolling = true;
                    return false;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                mIsBannerScolling = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
