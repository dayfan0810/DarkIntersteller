package cn.intersteller.darkintersteller.custview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class BubbleSortView extends ViewGroup {
    private ValueAnimator valueAnimator;

    public BubbleSortView(Context context) {
        this(context, null);
    }

    public BubbleSortView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BubbleSortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
