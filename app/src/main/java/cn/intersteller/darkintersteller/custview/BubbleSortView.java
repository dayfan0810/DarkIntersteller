package cn.intersteller.darkintersteller.custview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.intersteller.darkintersteller.R;

public class BubbleSortView extends ViewGroup {
    private ValueAnimator valueAnimator;
    private int bubble_sort_view_child_width;
    private int bubble_sort_view_child_height;
    private int bubble_sort_view_child_margin;
    private int bubble_sort_view_arrow_height;

    public BubbleSortView(Context context) {
        this(context, null);
    }

    public BubbleSortView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BubbleSortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init1();
    }

    private void init1() {
        setWillNotDraw(false);
        bubble_sort_view_child_width = getResources().getDimensionPixelSize(R.dimen.bubble_sort_view_child_width);
        bubble_sort_view_child_height = getResources().getDimensionPixelOffset(R.dimen.bubble_sort_view_child_height);
        bubble_sort_view_child_margin = getResources().getDimensionPixelOffset(R.dimen.bubble_sort_view_child_margin);
        bubble_sort_view_arrow_height = getResources().getDimensionPixelOffset(R.dimen.bubble_sort_view_arrow_height);
        valueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new MyBSViewAnimUpdateListener(this));
        valueAnimator.addListener(new MyBSAnimatorListener(this));

    }

    public void createBubbleTextView(int num) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.include_bubble_sort_child, null);
        textView.setText(String.valueOf(num));
        textView.setLayoutParams(new LayoutParams(bubble_sort_view_child_width, bubble_sort_view_child_height));
        addView(textView);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        if (childCount == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(((childCount - 1) *
                        bubble_sort_view_child_margin) + (bubble_sort_view_child_width * childCount),
                bubble_sort_view_arrow_height + bubble_sort_view_child_height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = bubble_sort_view_arrow_height;
        int i7 = 0;
//        while (i5 < childCount) {
//            View childAt = getChildAt(i5);
//            int i8 = (int) (((float) (( l - this.h) * (this.a + this.c))) * this.g);
//            if (i5 == this.h) {
//                childAt.layout(i7 + i8, i6, (i8 + i7) + this.a, this.b + i6);
//            } else if (i5 == this.i) {
//                childAt.layout(i7 - i8, i6, (this.a + i7) - i8, this.b + i6);
//            } else {
//                childAt.layout(i7, i6, this.a + i7, this.b + i6);
//            }
//            i7 += this.a + this.c;
//            i5++;
//        }
//
//    }
    }

    class MyBSViewAnimUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        final BubbleSortView bubbleSortView;

        MyBSViewAnimUpdateListener(BubbleSortView bubbleSortView) {
            this.bubbleSortView = bubbleSortView;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float v = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.bubbleSortView.requestLayout();
        }
    }

    class MyBSAnimatorListener implements Animator.AnimatorListener {
        final BubbleSortView bubbleSortView;

        MyBSAnimatorListener(BubbleSortView bubbleSortView) {
            this.bubbleSortView = bubbleSortView;
        }


        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
