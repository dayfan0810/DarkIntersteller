package cn.intersteller.darkintersteller.sort;


import androidx.annotation.UiThread;

import cn.intersteller.darkintersteller.custview.BubbleSortViewByCanvas;

public class SortAlgorithmThread extends AlgorithmThread implements IDataHandler {
    public static final String TAG = SortAlgorithmThread.class.getSimpleName();
    private boolean isPrepared = false;
    public BubbleSortViewByCanvas mSortView;
    private boolean swapAnimateEnable = true;

    public void prepare() {
        if (!this.isPrepared) {
            start();
            prepareHandler(this);
            this.isPrepared = true;
        }
    }

    @UiThread
    public void setData(final int[] array) {
        prepare();
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
//                SortAlgorithmThread.this.mSortView.setCompletePosition(-1);
                SortAlgorithmThread.this.mSortView.setArray(array);
                SortAlgorithmThread.this.mSortView.setTime(0);
                SortAlgorithmThread.this.mSortView.invalidate();
            }
        });
        sendData(array);
    }

    @UiThread
    public void onSwapping(final int one, final int two) {
        if (this.swapAnimateEnable) {
            long timeSleep;
            this.mSortView.setSwapPosition(one, two, false);
            int delta = this.mSortView.getDelta();
            if (delta == 0) {
                timeSleep = 1;
            } else {
                timeSleep = (long) ((((double) this.delayTime) * 1.5d) / ((double) delta));
            }
            while (delta >= 0) {
                fragment.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        SortAlgorithmThread.this.mSortView.incPositionSwap(2.0f);
                    }
                });
                sleepFor(timeSleep);
                delta -= 2;
            }
            return;
        }
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.setSwapPosition(one, two);
            }
        });
        sleep();
    }

    @UiThread
    public void onSwapped() {
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.setSwapPosition(-1, -1);
            }
        });
        sleep();
    }

    @UiThread
    public void onSwapped(boolean b) {
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.setSwapPosition(-1, -1);
            }
        });
    }

    public void onTrace(final int position) {
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.setTracePosition(position);
            }
        });
    }

    public void onTarget(final int position) {
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.setTargetPosition(position);
            }
        });
    }

    public void onDataReceived(Object data) {
    }

    public void onMessageReceived(String message) {
    }

//    public void onCompleted() {
//        finishSorting();
//        super.onCompleted();
//    }

    private void finishSorting() {
        for (int i = 0; i < this.mSortView.getSizeArray(); i++) {
            this.mSortView.setCompletePosition(i);
            fragment.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SortAlgorithmThread.this.mSortView.invalidate();
                }
            });
            sleepFor(this.delayTime / 3);
        }
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.setTracePosition(-1);
                SortAlgorithmThread.this.mSortView.invalidate();
            }
        });
    }

    public void sleep() {
        fragment.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SortAlgorithmThread.this.mSortView.addTimeUnit(1);
                SortAlgorithmThread.this.mSortView.invalidate();
            }
        });
        super.sleep();
    }

    public boolean isSwapAnimateEnable() {
        return this.swapAnimateEnable;
    }

    public void setSwapAnimateEnable(boolean swapAnimateEnable) {
        this.swapAnimateEnable = swapAnimateEnable;
    }
}