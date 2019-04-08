package cn.intersteller.darkintersteller.utils;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;


public class NetKit {
    private static final String TAG = "NetKit";

    private NetKit() {
    }


    public static void getNewslistByPage(Object tag, int page, String type, BaseCallback baseCallback) {
        HttpParams params = new HttpParams();
        params.put("type", type);
        params.put("page", page);
        params.put("_", System.currentTimeMillis());
        Log.i(TAG, "getNewslistByPage");
        OkGo.get(Configure.NEWS_LIST_URL)
                .tag(tag)
                .params(params)
                .execute(baseCallback);
    }

}
