package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.NewsBean;
import cn.intersteller.darkintersteller.utils.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class CnBetaFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    Log.i("deng111", "case 0:");
                    break;
                case 1:
                    Log.i("deng111", "case 1:");
                    break;
            }
        }
    };
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Banner mBanner;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cnbeta_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.cnbeta_fragment_swipeRefreshLayout);
        mBanner = view.findViewById(R.id.cnbeta_fragment_banner);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.cnbeta_fragment_recyclerView);
        onRefresh();
        return view;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        mNewsBeanList.clear();
        requestNews();
    }


    public void requestNews() {
        testOkhttpGet();
    }


    private void testOkhttpGet() {
        String url = "http://api.k780.com/?app=weather.history";
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        params.put("type", "all");
        params.put("page", 1+"");
        params.put("_", System.currentTimeMillis()+"");
        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(Constant.CNBETA_NEWS_LIST_URL).build();
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


        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = 0;
                message.obj = e.getMessage();
                mHandler.sendMessage(message);
                Log.i("deng111", "onFailure: " + message.obj.toString());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = response.body().string();//string不能调用两次 被调用一次就关闭了，这里调用两次会报异常
                mHandler.sendMessage(message);
                Log.i("deng111", "response: " + message.obj.toString());
            }
        });

//    BaseResponseObjectResponse newsPage = new BaseResponseObjectResponse<NewsListObject>(
//            new TypeToken<ResponseObject<NewsListObject>>() {
//            }) {
//        private int size = 0;
//        private List<NewsItem> itemList;
//
//        @Override
//        public ResponseObject<NewsListObject> convertResponse(Response response) throws Throwable {
//            Log.i("deng111", "convertResponse,response = " + response);
//            Log.i("deng111", "itemList = "+itemList.size());
//
//            int offsetFirst = -1;
//            int offsetSecond = -1;
//            int offsetThird = -1;
//            boolean findFirst = false;
//            boolean findSecond = false;
//            boolean findThird = false;
//            ResponseObject<NewsListObject> responseObject = super.convertResponse(response);
//            boolean calNew = responseObject.getResult().getPage() == 1;
//            itemList = responseObject.getResult().getList();
//            Log.i("deng111", "itemList = "+itemList.size());
//            for (NewsItem item : itemList) {
//                Log.i("deng111", "item = "+item.getContent());
//                if (item.getCounter() != null && item.getComments() != null) {
//                    String title = item.getTitle();
//                    int num = Integer.parseInt(item.getCounter());
//                    if (num > 9999) {
//                        item.setCounter("9999+");
//                    }
//                    num = Integer.parseInt(item.getComments());
//                    if (num > 999) {
//                        item.setComments("999+");
//                    }
//                } else {
//                    item.setCounter("0");
//                    item.setComments("0");
//                }
//                item.setTitle(item.getTitle().replaceAll("<.*?>", ""));
//                StringBuilder sb = new StringBuilder(
//                        Html.fromHtml(item.getHometext().replaceAll("<.*?>|[\\r|\\n]", "")));
//                if (sb.length() > 140) {
//                    item.setSummary(sb.replace(140, sb.length(), "...").toString());
//                } else {
//                    item.setSummary(sb.toString());
//                }
//                if (item.getThumb().contains("thumb")) {
//                    item.setLargeImage(
//                            item.getThumb().replaceAll("(\\.\\w{3,4})?_100x100|thumb/mini/", ""));
//                }
//
//            }
//
//            return responseObject;
//        }
//
//        /**
//         * @param result
//         */
//        @Override
//        protected void onSuccess(NewsListObject result) {
//            Log.i("deng111", "onSuccess = ");
//
//        }
//
//
//
//        @Override
//        public void onFinish() {
//
//        }
//    };


    }
}
