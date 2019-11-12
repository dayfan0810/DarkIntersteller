package cn.intersteller.darkintersteller.utils;

import android.app.Activity;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import okhttp3.Response;

public abstract class BaseResponseObjectResponse<T> extends BaseGsonCallback<ResponseObject<T>> {

    protected BaseResponseObjectResponse(TypeToken<ResponseObject<T>> type) {
        super(type);
    }

    @Override
    protected void onError(int httpCode, Response response, Throwable cause) {

    }

    @Override
    protected final void onResponse(ResponseObject<T> object) {
        Log.i("deng111", "1111111111111111111111");
        if (object != null) {
            if ("success".equals(object.getState())) {
                Log.i("deng111", "1111111111111111111111");
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
