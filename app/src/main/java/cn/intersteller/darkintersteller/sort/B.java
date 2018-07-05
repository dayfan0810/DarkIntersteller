package cn.intersteller.darkintersteller.sort;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class B {
    private static ExecutorService a = null;
    private static Handler b = null;
    private static final int c = Runtime.getRuntime().availableProcessors();
    private static final int d = (c + 1);
    private static final int e = ((c * 2) + 1);
    private static final BlockingQueue<Runnable> f = new LinkedBlockingQueue();

    public static void a(A aVar) {
        b();
        a.submit(aVar);
    }

    public static void a(long j, A aVar) {
        a();
        b.postDelayed(aVar, j);
    }

    public static void b(A aVar) {
        a();
        b.post(aVar);
    }

    private static synchronized void a() {
        synchronized (B.class) {
            if (b == null) {
                b = new Handler(Looper.getMainLooper());
            }
        }
    }

    private static synchronized void b() {
        synchronized (B.class) {
            if (a == null) {
                a = new ThreadPoolExecutor(d, e, 1, TimeUnit.SECONDS, f, new ThreadPoolExecutor.DiscardOldestPolicy());
            }
        }
    }
}