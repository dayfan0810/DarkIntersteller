package cn.intersteller.darkintersteller.utils;

import android.app.Activity;

import com.google.gson.reflect.TypeToken;

import okhttp3.Response;

/**
 * cnBetaReader
 * <p/>
 * Created by 远望の无限(ywwxhz) on 2014/11/2 18:01.
 */
public abstract class BaseResponseObjectResponse<T> extends BaseGsonCallback<ResponseObject<T>> {

    protected BaseResponseObjectResponse(TypeToken<ResponseObject<T>> type) {
        super(type);
    }

    @Override
    protected void onError(int httpCode, Response response, Throwable cause) {

    }

    @Override
    protected final void onResponse(ResponseObject<T> object) {
        if (object != null) {
            if ("success".equals(object.getState())) {
                onSuccess(object.getResult());
            } else {
                onError(200, null, new RuntimeException("empty ResponseObject"));
            }
        }
    }

    /**
     * 成功调用
     *
     * @param result
     */
    protected abstract void onSuccess(T result);

    /**
     * 获取Activity
     *
     * @return {@link Activity}
     */
}
