package cn.intersteller.darkintersteller.custview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;

import cn.intersteller.darkintersteller.R;

public class BubbleSortViewByCanvas extends View {

    private int targetColor ;
    private int traceColor ;
    private int quadColor ;
    private int completeColor ;
    private int textInfoColor;
    private int swapAColor;
    private int swapBColor;
    private int barColor ;


    private static final String NO_DATA = "No Data!";
    public static final String TAG = "SortView";
    private int[] mArray;
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

    public BubbleSortViewByCanvas(Context context) {
        this(context, null);
    }

    public BubbleSortViewByCanvas(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleSortViewByCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public int getCompletePosition() {
        return completePosition;
    }

    public void setCompletePosition(int completePosition) {
        completePosition = completePosition;
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

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Log.i("deng", "init111");
        setWillNotDraw(false);
        context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SortViewAttrs);
        barColor = a.getColor(R.styleable.SortViewAttrs_barColor, 0xFF00bbff);
        targetColor = a.getColor(R.styleable.SortViewAttrs_targetColor, Color.YELLOW);
        traceColor = a.getColor(R.styleable.SortViewAttrs_traceColor, Color.DKGRAY);
//        quadColor = a.getInteger(R.styleable.SortViewAttrs_quadColor,  Color.BLUE);
//        completeColor = a.getInteger(R.styleable.SortViewAttrs_completeColor, Color.BLUE);
        textInfoColor = a.getColor(R.styleable.SortViewAttrs_textInfoColor,  Color.BLUE);
        swapAColor = a.getColor(R.styleable.SortViewAttrs_swapAColor, Color.RED);
        swapBColor = a.getColor(R.styleable.SortViewAttrs_swapBColor, Color.GREEN);
        a.recycle();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        setTextSize(20.0f);
        mArray = null;
        invalidate();
    }

    private void setTextSize(float GESTURE_THRESHOLD_DIP) {
        mPaint.setTextSize((float) ((int) ((GESTURE_THRESHOLD_DIP * getContext().getResources().getDisplayMetrics().density) + 0.5f)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mArray == null || mArray.length <= 0) {
            Log.i("deng", "ondraw --- no data");
            drawNoData(canvas);
        } else {
            Log.i("deng", "ondraw --- drwa array");
            drawArrayByVertical(canvas);
//            drawArrayByHorizental(canvas);
        }
        drawInfo(canvas);
    }

    private void drawArrayByVertical(Canvas canvas) {
        int width = getWidth();
        Log.i("deng", "width = " + width);
        int height = getHeight();
        Log.i("deng", "height = " + height);
        float barWidth = (float) (width / (mArray.length + 1));
        mPaint.setStrokeWidth(0.8f * barWidth);
        int max = getMax(mArray);
        float per = (float) (width / (max + 2));
        float x = 0.0f;
        float y = 0;
        int index = 0;
        for (int i = 0; i <mArray.length; i++) {
            y = y + barWidth;
            mPaint.setColor(barColor);
            canvas.drawLine(x, y, (((float) mArray[i]) * per), y, mPaint);
            index++;
        }
    }

    private void drawInfo(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(textInfoColor);
        setTextSize(10.0f);
        Rect rect = new Rect();
        mPaint.getTextBounds("A", 0, 1, rect);
        int x = rect.width();
        int y = rect.height() + (rect.height() / 2);
        canvas.drawText(name, (float) x, (float) y, mPaint);
        canvas.drawText("Complexity: " + mTime, (float) x, (float) (y + (rect.height() + (rect.height() / 2))), mPaint);
    }

    private void drawArrayByHorizental(Canvas canvas) {
        int width = getWidth();
        Log.i("deng", "width = " + width);
        int height = getHeight();
        Log.i("deng", "height = " + height);
        float barWidth = (float) (width / (mArray.length + 1));
        mPaint.setStrokeWidth(0.8f * barWidth);
        int max = getMax(mArray);
        float per = (float) (height / (max + 1));
        float x = 0.0f;
        float y = (float) height;
        int index = 0;
        for (int i = 0; i < mArray.length; i++) {
            x += barWidth;
            if (index <= completePosition) {
                Log.i("deng", "if");
                mPaint.setColor(completeColor);
                canvas.drawLine(x, y, x, y - (((float) mArray[i]) * per), mPaint);
            } else {
                if (index == swapAPosition) {
                    Log.i("deng", "else 1111");
                    mPaint.setColor(swapAColor);
                    canvas.drawLine(xA, yA, xA, yA + (((float) mArray[i]) * per), mPaint);
                } else if (index == swapBPosition) {
                    Log.i("deng", "else 2222");

                    mPaint.setColor(swapBColor);
                    canvas.drawLine(xB, yB, xB, yB + (((float) mArray[i]) * per), mPaint);
                } else if (index == tracePosition) {
                    Log.i("deng", "else 3333");

                    mPaint.setColor(traceColor);
                    canvas.drawLine(x, y, x, y - (((float) mArray[i]) * per), mPaint);
                } else {
                    Log.i("deng", "else 4444");

                    mPaint.setColor(barColor);
                    canvas.drawLine(x, y, x, y - (((float) mArray[i]) * per), mPaint);
                }
                if (index == targetPosition) {
                    Log.i("deng", "else 5555");

                    mPaint.setColor(targetColor);
                    canvas.drawLine(x, y, x, y - (((float) mArray[i]) * per), mPaint);
                }
            }
            index++;
        }
        if (swapAPosition != swapBPosition) {
            Log.i("deng", "if 6666");

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2.0f);
            mPaint.setColor(quadColor);
            Path path = new Path();
            path.moveTo(xA, yA);
            path.quadTo(xA + ((Math.abs(xB - xA) * 2.0f) / 3.0f), y - (((float) max) * per), xB, yB);
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawNoData(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(textInfoColor);
        canvas.drawText(NO_DATA, (float) (getWidth() / 2), (float) (getHeight() / 2), mPaint);
    }

    public void setTracePosition(int index) {
        tracePosition = index;
        invalidate();
    }

    public void setArray(int[] arr) {
        mArray = arr;
    }

    @UiThread
    public void setSwapPosition(int i1, int i2) {
        setSwapPosition(i1, i2, true);
    }

    @UiThread
    public void setSwapPosition(int i1, int i2, boolean redraw) {
        if (i1 < 0 || i2 < 0) {
            swapAPosition = i1;
            swapBPosition = i2;
            if (redraw) {
                invalidate();
                return;
            }
            return;
        }
        if (i1 < i2) {
            swapAPosition = i1;
            swapBPosition = i2;
        } else {
            swapAPosition = i2;
            swapBPosition = i1;
        }
        int width = getWidth();
        int height = getHeight();
        float barWidth = (float) (width / (mArray.length + 1));
        float per = (float) (height / (getMax(mArray) + 1));
        xA = ((float) (swapAPosition + 1)) * barWidth;
        yA = ((float) height) - (((float) mArray[swapAPosition]) * per);
        xB = ((float) (swapBPosition + 1)) * barWidth;
        yB = ((float) height) - (((float) mArray[swapBPosition]) * per);
        delta = (int) Math.abs(xB - xA);
        if (redraw) {
            invalidate();
        }
    }

    public int getSizeArray() {
        return mArray.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public void setName(int id) {
        name = context.getString(id);
    }

    @UiThread
    public void setTargetPosition(int targetPosition) {
        targetPosition = targetPosition;
        invalidate();
    }

    public void setTime(long time) {
        mTime = time;
    }

    public void addTimeUnit(long time) {
        mTime += time;
    }

    @UiThread
    public void incPositionSwap(float v) {
        xA += v;
        xB -= v;
        invalidate();
    }

    public int getDelta() {
        return delta;
    }
}