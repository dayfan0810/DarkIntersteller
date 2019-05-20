package cn.intersteller.darkintersteller.download;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class OkHttpRequestManager {

    //    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> downCalls;//用来存放各个下载的请求
    private OkHttpClient mClient;//OKHttpClient;

//    //获得一个单例类
//    public static OkHttpRequestManager getInstance() {
//        for (; ; ) {
//            OkHttpRequestManager current = INSTANCE.get();
//            if (current != null) {
//                return current;
//            }
//            current = new OkHttpRequestManager();
//            if (INSTANCE.compareAndSet(null, current)) {
//                return current;
//            }
//        }
//    }

    private OkHttpRequestManager() {
        downCalls = new HashMap<>();
        mClient = new OkHttpClient.Builder().build();
    }

}
