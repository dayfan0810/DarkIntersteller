package cn.intersteller.darkintersteller.login.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cn.intersteller.darkintersteller.login.model.IUser;
import cn.intersteller.darkintersteller.login.view.ILoginView;
import cn.intersteller.darkintersteller.utils.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginPresenterCompl implements ILoginPresenter {
    ILoginView iLoginView;
    IUser user;
    Handler handler;

    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
//		initUser();
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
//		Boolean isLoginSuccess = true;
//		final int code = user.checkUserValidity(name,passwd);
//		if (code!=0) isLoginSuccess = false;
//		final Boolean result = isLoginSuccess;
//		handler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//					iLoginView.onLoginResult(result, code);
//			}
//		}, 5000);

        StringBuilder userAndPasswordUrl = getUserAndPasswordUrl(name, passwd);
//        Log.i("dengsb", "userAndPasswordUrl = " + userAndPasswordUrl.toString());
        asyncValidate(userAndPasswordUrl.toString());
    }

    private StringBuilder getUserAndPasswordUrl(String name, String passwd) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.NETEASEBASE).append("login?email=").append(name).append("&password=").append(passwd);
        return sb;
    }


    /*
    okhttp异步POST请求 要求API level 21+
    account 本来想的是可以是 telphone或者username
    但目前只实现了telphone
   */
    private void asyncValidate(final String account) {
        /*
         发送请求属于耗时操作，所以开辟子线程执行
         上面的参数都加上了final，否则无法传递到子线程中
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
                OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        System.out.println("deng1 cookies url: " + cookies.toString());
//                        for (Cookie cookie : cookies)
//                        {
//                            System.out.println("cookies: " + cookie.toString());
//                        }
                        cookieStore.put(url.host(), cookies);

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
                Request request = new Request.Builder().url(account).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("deng-impl", "onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String responseText = response.body().string();
                        try {
                            boolean isLoginSuccess = true;
                            JSONObject jsonObject = new JSONObject(responseText);
                            String resultCode = (String) jsonObject.optString("code");
                            Log.i("deng-impl", "resultCode = " + resultCode);
                            if (!resultCode.equals("200")) {
                                isLoginSuccess = false;
                                return;
                            }
                            final Boolean result = true;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    iLoginView.onLoginResult(result, Integer.parseInt(resultCode));
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                });
            }
        }).start();
    }


    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }


//	private void initUser(){
//		user = new UserModel("mvp","mvp");
//	}
}
