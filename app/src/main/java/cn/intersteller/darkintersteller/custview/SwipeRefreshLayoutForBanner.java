package cn.intersteller.darkintersteller.custview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

public class SwipeRefreshLayoutForBanner extends SwipeRefreshLayout {
    public SwipeRefreshLayoutForBanner(@NonNull Context context) {
        super(context);
    }

    public SwipeRefreshLayoutForBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
