package cn.intersteller.darkintersteller.custview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

public class AutofitTextView extends androidx.appcompat.widget.AppCompatTextView {
    private float mDefaultTextSize;
    private Paint mTextPaint;

    public AutofitTextView(Context context) {
        this(context, null);
    }

    public AutofitTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutofitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.set(getPaint());
        mDefaultTextSize = getTextSize();
    }

    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        refitText(text.toString(), getWidth());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refitText(getText().toString(), getWidth());

    }


    public void refitText(String text, int textWidth) {
        Log.e("refit", "refit:" + text + "width:" + textWidth);
        if (textWidth > 0) {
            int availableTextWidth = textWidth - getPaddingLeft() - getPaddingRight();
            float tsTextSize = mDefaultTextSize;
            mTextPaint.setTextSize(tsTextSize);
            float length = mTextPaint.measureText(text);
            while (length > availableTextWidth) {
                tsTextSize--;
                mTextPaint.setTextSize(tsTextSize);
                length = mTextPaint.measureText(text);
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, tsTextSize);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
