package cn.intersteller.darkintersteller;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.model.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.outterfragment.FirstFragment;
import cn.intersteller.darkintersteller.outterfragment.SecFragment;
import cn.intersteller.darkintersteller.outterfragment.ThirdFragment;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }
    public static List<Fragment> mFragmntList = new ArrayList<>(3);

//    private OutterFragmentAdapter outterFragmentAdapter;
//    private UshkNewsGrabber mUshkNewsGrabber = new UshkNewsGrabber();
//    private CnbetaNewsGrabber mCnbetaNewsGrabber = new CnbetaNewsGrabber();
    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
            initOKHttpClient();
//            EventBus.getDefault().register(this);
        }
        addFragmet();
    }

    private void addFragmet() {
        mFragmntList.add(FirstFragment.newInstance());
        mFragmntList.add(SecFragment.newInstance());
        mFragmntList.add(ThirdFragment.newInstance());

    }

    public void initOKHttpClient() {
//        HttpHelper.init(new OkHttpProcessor(this));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Referer", "http://www.cnbeta.com/");
        headers.put("Origin", "http://www.cnbeta.com");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36");
        OkGo.getInstance().init(this)//
                .setOkHttpClient(builder.build()).setCacheMode(CacheMode.NO_CACHE).addCommonHeaders(headers); // 设置全局公共头
    }


}
