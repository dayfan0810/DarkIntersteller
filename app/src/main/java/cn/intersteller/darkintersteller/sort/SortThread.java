package cn.intersteller.darkintersteller.sort;

public class SortThread {
    protected Thread mThread;

    protected void waitThread() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public Thread getmThread() {
        return mThread;
    }

}
