package cn.intersteller.darkintersteller.custview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Array;

import cn.intersteller.darkintersteller.R;

public class BubbleSortView extends View {

    private int targetColor = -16711936;
    private int traceColor = -16776961;
    private int quadColor = -16711936;
    private int completeColor = -16711936;
    private int textInfoColor = SupportMenu.CATEGORY_MASK;
    private int swapAColor = SupportMenu.CATEGORY_MASK;
    private int swapBColor = -65281;


    private static final String NO_DATA = "No Data!";
    public static final String TAG = "SortView";
    private int[] array;
    private int barColor = -1;
    private int completePosition = -1;
    private Context context;
    private int delta = 0;
    private Handler handler = new Handler();
    private boolean isDrawing = false;
    private Paint mPaint;
    private long mTime = 0;
    private String name = "";
    private int swapAPosition = -1;
    private int swapBPosition = -1;
    private int targetPosition = -1;
    private float[][] tmp = ((float[][]) Array.newInstance(Float.TYPE, new int[]{2, 2}));
    private int tracePosition = -1;
    public float xA = 0.0f;
    public float xB = 0.0f;
    private float yA = 0.0f;
    private float yB = 0.0f;

    public BubbleSortView(Context context) {
        super(context);
        setup(context, null, -1);
    }

    public BubbleSortView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs, -1);
    }

    public BubbleSortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs, defStyleAttr);
    }

    public int getCompletePosition() {
        return this.completePosition;
    }

    public void setCompletePosition(int completePosition) {
        this.completePosition = completePosition;
    }

    private int getMax(int[] arr) {
        int N = arr.length;
        int max = arr[0];
        for (int i = 1; i < N; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public void setup(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SortViewAttrs);
        this.barColor = a.getInteger(R.styleable.SortViewAttrs_barColor, this.barColor);
        this.barColor = a.getInteger(R.styleable.SortViewAttrs_barColor, this.barColor);

        this.targetColor = a.getInteger(R.styleable.SortViewAttrs_targetColor, this.targetColor);
        this.traceColor = a.getInteger(R.styleable.SortViewAttrs_traceColor, this.traceColor);
        this.quadColor = a.getInteger(R.styleable.SortViewAttrs_quadColor, this.quadColor);
        this.completeColor = a.getInteger(R.styleable.SortViewAttrs_completeColor, this.completeColor);
        this.textInfoColor = a.getInteger(R.styleable.SortViewAttrs_textInfoColor, this.textInfoColor);
        this.swapAColor = a.getInteger(R.styleable.SortViewAttrs_swapAColor, this.swapAColor);
        this.swapBColor = a.getInteger(R.styleable.SortViewAttrs_swapBColor, this.swapBColor);
        a.recycle();
        this.mPaint = new Paint();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.barColor);
        this.mPaint.setAntiAlias(true);
        setTextSize(20.0f);
        this.array = null;
    }

    private void setTextSize(float GESTURE_THRESHOLD_DIP) {
        this.mPaint.setTextSize((float) ((int) ((GESTURE_THRESHOLD_DIP * getContext().getResources().getDisplayMetrics().density) + 0.5f)));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.array == null || this.array.length <= 0) {
            drawNoData(canvas);
        } else {
            drawArray(canvas);
        }
        drawInfo(canvas);
    }

    private void drawInfo(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setColor(this.textInfoColor);
        setTextSize(10.0f);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("A", 0, 1, rect);
        int x = rect.width();
        int y = rect.height() + (rect.height() / 2);
        canvas.drawText(this.name, (float) x, (float) y, this.mPaint);
        canvas.drawText("Complexity: " + this.mTime, (float) x, (float) (y + (rect.height() + (rect.height() / 2))), this.mPaint);
    }

    private void drawArray(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float barWidth = (float) (width / (this.array.length + 1));
        this.mPaint.setStrokeWidth(0.8f * barWidth);
        int max = getMax(this.array);
        float per = (float) (height / (max + 1));
        float x = 0.0f;
        float y = (float) height;
        int index = 0;
        for (int a : this.array) {
            x += barWidth;
            if (index <= this.completePosition) {
                this.mPaint.setColor(this.completeColor);
                canvas.drawLine(x, y, x, y - (((float) a) * per), this.mPaint);
            } else {
                if (index == this.swapAPosition) {
                    this.mPaint.setColor(this.swapAColor);
                    canvas.drawLine(this.xA, this.yA, this.xA, this.yA + (((float) a) * per), this.mPaint);
                } else if (index == this.swapBPosition) {
                    this.mPaint.setColor(this.swapBColor);
                    canvas.drawLine(this.xB, this.yB, this.xB, this.yB + (((float) a) * per), this.mPaint);
                } else if (index == this.tracePosition) {
                    this.mPaint.setColor(this.traceColor);
                    canvas.drawLine(x, y, x, y - (((float) a) * per), this.mPaint);
                } else {
                    this.mPaint.setColor(this.barColor);
                    canvas.drawLine(x, y, x, y - (((float) a) * per), this.mPaint);
                }
                if (index == this.targetPosition) {
                    this.mPaint.setColor(this.targetColor);
                    canvas.drawLine(x, y, x, y - (((float) a) * per), this.mPaint);
                }
            }
            index++;
        }
        if (this.swapAPosition != this.swapBPosition) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setColor(this.quadColor);
            Path path = new Path();
            path.moveTo(this.xA, this.yA);
            path.quadTo(this.xA + ((Math.abs(this.xB - this.xA) * 2.0f) / 3.0f), y - (((float) max) * per), this.xB, this.yB);
            canvas.drawPath(path, this.mPaint);
        }
    }

    private void drawNoData(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setColor(this.textInfoColor);
        canvas.drawText(NO_DATA, (float) (getWidth() / 2), (float) (getHeight() / 2), this.mPaint);
    }

    public void setTracePosition(int index) {
        this.tracePosition = index;
        invalidate();
    }

    public void setArray(int[] arr) {
        this.array = arr;
    }

    @UiThread
    public void setSwapPosition(int i1, int i2) {
        setSwapPosition(i1, i2, true);
    }

    @UiThread
    public void setSwapPosition(int i1, int i2, boolean redraw) {
        if (i1 < 0 || i2 < 0) {
            this.swapAPosition = i1;
            this.swapBPosition = i2;
            if (redraw) {
                invalidate();
                return;
            }
            return;
        }
        if (i1 < i2) {
            this.swapAPosition = i1;
            this.swapBPosition = i2;
        } else {
            this.swapAPosition = i2;
            this.swapBPosition = i1;
        }
        int width = getWidth();
        int height = getHeight();
        float barWidth = (float) (width / (this.array.length + 1));
        float per = (float) (height / (getMax(this.array) + 1));
        this.xA = ((float) (this.swapAPosition + 1)) * barWidth;
        this.yA = ((float) height) - (((float) this.array[this.swapAPosition]) * per);
        this.xB = ((float) (this.swapBPosition + 1)) * barWidth;
        this.yB = ((float) height) - (((float) this.array[this.swapBPosition]) * per);
        this.delta = (int) Math.abs(this.xB - this.xA);
        if (redraw) {
            invalidate();
        }
    }

    public int getSizeArray() {
        return this.array.length;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(int id) {
        this.name = this.context.getString(id);
    }

    @UiThread
    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
        invalidate();
    }

    public void setTime(long time) {
        this.mTime = time;
    }

    public void addTimeUnit(long time) {
        this.mTime += time;
    }

    @UiThread
    public void incPositionSwap(float v) {
        this.xA += v;
        this.xB -= v;
        invalidate();
    }

    public int getDelta() {
        return this.delta;
    }
}