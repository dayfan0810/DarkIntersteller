package cn.intersteller.darkintersteller.sort;


import android.app.Activity;

import cn.intersteller.darkintersteller.custview.BubbleSortViewOld;


public class BubbleSortThread extends SortAlgorithmThread {
    int[] array;

    public BubbleSortThread(BubbleSortViewOld sortView, Activity activity) {
        this.mSortView = sortView;
        this.activity = activity;
    }

    public void sort() {
        for (int i = this.array.length - 1; i >= 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                onTrace(j);
                sleep();
                if (this.array[j] > this.array[j + 1]) {
                    onSwapping(j, j + 1);
                    int temp = this.array[j];
                    this.array[j] = this.array[j + 1];
                    this.array[j + 1] = temp;
                    onSwapped();
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        onCompleted();
    }

    public void run() {
        super.run();
    }

    public void onDataReceived(Object data) {
        super.onDataReceived(data);
        this.array = (int[]) data;
    }

    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(AlgorithmThread.COMMAND_START_ALGORITHM)) {
            startExecution();
            sort();
        }
    }
}