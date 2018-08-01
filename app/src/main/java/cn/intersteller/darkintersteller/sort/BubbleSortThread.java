package cn.intersteller.darkintersteller.sort;



import android.support.v4.app.Fragment;

import cn.intersteller.darkintersteller.custview.BubbleSortViewByCanvas;


public class BubbleSortThread extends SortAlgorithmThread {
    int[] mArray;

    public BubbleSortThread(BubbleSortViewByCanvas sortView, Fragment fragment) {
        mSortView = sortView;
        this.fragment = fragment;
    }

    public void sort() {
        for (int i = mArray.length - 1; i >= 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                onTrace(j);
                sleep();
                if (mArray[j] > mArray[j + 1]) {
                    onSwapping(j, j + 1);
                    int temp = mArray[j];
                    mArray[j] = mArray[j + 1];
                    mArray[j + 1] = temp;
                    onSwapped();
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
//        onCompleted();
    }

    public void run() {
        super.run();
    }

    public void onDataReceived(Object data) {
        super.onDataReceived(data);
        mArray = (int[]) data;
    }

    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(AlgorithmThread.COMMAND_START_ALGORITHM)) {
            startExecution();
            sort();
        }
    }
}