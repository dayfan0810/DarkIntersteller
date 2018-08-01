package cn.intersteller.darkintersteller.sort;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.util.concurrent.atomic.AtomicBoolean;


public class AlgorithmThread extends HandlerThread {
    public static final String COMMAND_START_ALGORITHM = "start";
    public static final String KEY_ALGORITHM = "KEY_ALGORITHM";
    private static final String TAG = "AlgorithmThread";
    protected Fragment fragment;
//    private SortCompletionListener completionListener;
    protected long delayTime = 50;
    private Handler mHandler;
//    private LogView mLogger;
    private final Object pauseLock = new Object();
    private final AtomicBoolean paused = new AtomicBoolean(false);
    private boolean started;

    public static final class ALGORITHM_NAME {
        public static final String BOGO_SORT = "BOGO_SORT";
        public static final String BUBBLE_SORT = "BUBBLE_SORT";
        public static final String COCKTAIL_SORT = "COCKTAIL_SORT";
        public static final String COUNTING_SORT = "COUNTING_SORT";
        public static final String INSERTION_SORT = "INSERTION_SORT";
        public static final String MERGE_SORT = "MERGE_SORT";
        public static final String QUICK_SORT = "QUICK_SORT";
        public static final String SELECTION_SORT = "SELECTION_SORT";
        public static final String SHELL_SORT = "SHELL_SORT";
    }

    public AlgorithmThread() {
        super("");
    }

    public long getDelayTime() {
        return this.delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public void sleep() {
        sleepFor(this.delayTime);
    }

    public void sleepFor(long time) {
        try {
            sleep(time);
            if (isPaused()) {
                pauseExecution();
            } else {
                resumeExecution();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

//    public String getString(int resID) {
//        return this.activity.getString(resID);
//    }

    public void startExecution() {
        this.started = true;
        sleepFor(this.delayTime * 2);
    }

    private void pauseExecution() {
        if (this.paused.get()) {
            synchronized (getPauseLock()) {
                if (this.paused.get()) {
                    try {
                        getPauseLock().wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    private void resumeExecution() {
        synchronized (this.pauseLock) {
            this.pauseLock.notifyAll();
        }
    }

    private Object getPauseLock() {
        return this.pauseLock;
    }

    public boolean isPaused() {
        return this.paused.get();
    }

    public void setPaused(boolean b) {
        this.paused.set(b);
        if (!b) {
            synchronized (getPauseLock()) {
                getPauseLock().notify();
            }
        }
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

//    public void showLog(final String log) {
//        this.activity.runOnUiThread(new Runnable() {
//            public void run() {
//                if (AlgorithmThread.this.mLogger != null) {
//                    AlgorithmThread.this.mLogger.addLog(log);
//                }
//            }
//        });
//    }
//
//    public void showLog(final String message, final int[] array) {
//        this.activity.runOnUiThread(new Runnable() {
//            public void run() {
//                if (AlgorithmThread.this.mLogger != null) {
//                    AlgorithmThread.this.mLogger.addLog(message, (int[]) array.clone());
//                }
//            }
//        });
//    }

//    public void setCompletionListener(SortCompletionListener completionListener) {
//        this.completionListener = completionListener;
//    }

    public void prepareHandler(final IDataHandler dataHandler) {
        this.mHandler = new Handler(getLooper(), new Callback() {
            public boolean handleMessage(Message msg) {
                if (msg.obj instanceof String) {
                    dataHandler.onMessageReceived((String) msg.obj);
                } else {
                    dataHandler.onDataReceived(msg.obj);
                }
                return true;
            }
        });
    }

    public void sendData(Object data) {
        this.mHandler.obtainMessage(1, data).sendToTarget();
    }

    public void sendMessage(String message) {
        this.mHandler.obtainMessage(1, message).sendToTarget();
    }

//    public void onCompleted() {
//        if (this.completionListener != null) {
//            this.completionListener.onSortCompleted();
//        }
//        this.started = false;
//    }
//
//    public void setLogger(LogView mLogger) {
//        this.mLogger = mLogger;
//    }
}