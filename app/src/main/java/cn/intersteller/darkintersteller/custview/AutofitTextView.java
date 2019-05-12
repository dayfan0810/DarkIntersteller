package cn.intersteller.darkintersteller.custview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class AutofitTextView extends android.support.v7.widget.AppCompatTextView {
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
    }

    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);

    }
}
