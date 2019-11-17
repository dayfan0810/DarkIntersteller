package cn.intersteller.darkintersteller.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import cn.intersteller.darkintersteller.net.PersistentCookieStore;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    static volatile HttpUtil defaultInstance;
    private static OkHttpClient mOkHttpClient;


    private HttpUtil() {

        final PersistentCookieStore cookieStore = new PersistentCookieStore();

        //创建cookieJar 用来保存登录的状态
        CookieJar cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                if (cookies != null && cookies.size() > 0) {
                    for (Cookie item : cookies) {
                        cookieStore.add(url, item);
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                Log.i("deng2", "hahahahaah222222 url.host() " + url.host());
                List<Cookie> cookies = cookieStore.get(url);
                return cookies;
            }
        };
        //通过newBuilder设置cookjar并构建OkHttpClient对象
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .cookieJar(cookieJar)
                .build();
    }

    public static HttpUtil getHttpUtilInstance() {
        if (defaultInstance == null) {
            synchronized (HttpUtil.class) {
                if (defaultInstance == null) {
                    defaultInstance = new HttpUtil();
                }
            }
        }
        return defaultInstance;
    }

    public void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        Request request = new Request.Builder().url(address).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    public static Request makeCnbetaRequest(int page) {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        params.put("type", "all");
        params.put("page", page + "");
        params.put("_", System.currentTimeMillis() + "");
        headers.put("Referer", "http://www.cnbeta.com/");
        headers.put("Origin", "http://www.cnbeta.com");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.CNBETA_NEWS_LIST_URL).newBuilder();

        for (String key : params.keySet()) {
            urlBuilder.setQueryParameter(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .headers(headers == null ? new Headers.Builder().build() : Headers.of(headers))
                .get()
                .build();
        return request;
    }


    public static Request makeJin10Request() {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
//        params.put("type", "all");
//        params.put("_", System.currentTimeMillis() + "");
        headers.put("Referer", Constant.JIN10_BASE_URL);
        headers.put("Origin", Constant.JIN10_BASE_URL);
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36");
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.JIN10_BASE_URL).newBuilder();

        for (String key : params.keySet()) {
            urlBuilder.setQueryParameter(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .headers(headers == null ? new Headers.Builder().build() : Headers.of(headers))
                .get()
                .build();
        return request;
    }

}
