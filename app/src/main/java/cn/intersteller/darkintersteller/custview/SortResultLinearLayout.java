package cn.intersteller.darkintersteller.custview;

import android.content.Context;

import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cn.intersteller.darkintersteller.R;

public class SortResultLinearLayout extends LinearLayout {

    private int sort_result_view_child_width;
    private int sort_result_view_child_height;
    private int sort_result_view_child_margin;

    public SortResultLinearLayout(Context context) {
        super(context,null);
    }

    public SortResultLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SortResultLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        sort_result_view_child_width = getResources().getDimensionPixelSize(R.dimen.sort_result_view_child_width);
        sort_result_view_child_height = getResources().getDimensionPixelOffset(R.dimen.sort_result_view_child_height);
        sort_result_view_child_margin = getResources().getDimensionPixelOffset(R.dimen.sort_result_view_child_margin);
        if (attrs != null) {
//            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.style.S);
//            sort_result_view_child_width = obtainStyledAttributes.getResourceId(0, -1);
//            sort_result_view_child_height = obtainStyledAttributes.getResourceId(1, -1);
//            obtainStyledAttributes.recycle();
        }

    }
}
