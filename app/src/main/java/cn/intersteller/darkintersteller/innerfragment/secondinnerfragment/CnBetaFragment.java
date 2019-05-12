package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.adapter.CnbetaNewsRecyclerViewAdapter;
import cn.intersteller.darkintersteller.bean.CnbetaNewsBean;
import cn.intersteller.darkintersteller.utils.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class CnBetaFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    public List<CnbetaNewsBean> mCnbetaNewsBeanList = new ArrayList<>();
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
//    private Banner mBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cnbeta_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.cnbeta_fragment_swipeRefreshLayout);
//        mBanner = view.findViewById(R.id.cnbeta_fragment_banner);

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
        mCnbetaNewsBeanList.clear();
        requestNews();
    }


    private void requestNews() {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        params.put("type", "all");
        params.put("page", 1 + "");
        params.put("_", System.currentTimeMillis() + "");
        OkHttpClient client = new OkHttpClient();
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
            public CnbetaNewsRecyclerViewAdapter newsAdapter;

            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(), "获取Cnbeta新闻信息失败", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
                Looper.loop();
                /*
                也可以如下
                 getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "获取新闻信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
                 */
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("deng11111", "responseText =  " + responseText);

                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    String state = jsonObject.optString("state");
                    Log.i("deng111", "state =  " + state);

                    if (!state.equals("success")) {
                        return;
                    }
                    //使用打断点找到text.右键赋值value
                    JSONObject jsonObject_result = jsonObject.getJSONObject("result");
                    JSONArray list = jsonObject_result.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject dataItem = (JSONObject) list.get(i);
                        String title = dataItem.optString("title");

                        String hometext = dataItem.optString("hometext");
                        String mview = dataItem.optString("mview");
                        String inputtime = dataItem.optString("inputtime");
                        String thumb = dataItem.optString("thumb");
                        String url_show = dataItem.optString("url_show");
                        CnbetaNewsBean newsBean = new CnbetaNewsBean();
                        newsBean.setTitle(title);
                        newsBean.setHometext(hometext);
                        newsBean.setMview(mview);
                        newsBean.setInputtime(inputtime);
                        newsBean.setThumb(thumb);
                        newsBean.setUrl_show(url_show);
                        Log.i("deng1111", "url_show =  " + url_show);
                        mCnbetaNewsBeanList.add(newsBean);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(manager);
                            Log.i("deng111", "mNewsBeanList.size =  " + mCnbetaNewsBeanList.size());

                            newsAdapter = new CnbetaNewsRecyclerViewAdapter(getContext(), mCnbetaNewsBeanList, mRecyclerView, manager);
                            newsAdapter.setmOnItemClickListener(new CnbetaNewsRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Log.i("deng111", "onItemClick ");
                                    if (mCnbetaNewsBeanList.size() <= 0) {
                                        Log.i("deng111", "onItemClick no data, return");
                                        return;
                                    }
                                    CnbetaNewsBean item = newsAdapter.getItem(position);

                                }

                                @Override
                                public void onItemLongClick(View view, int position) {
                                    Log.i("deng", "onItemLongClick ");


                                }
                            });
                            mRecyclerView.setAdapter(newsAdapter);
                            mSwipeRefreshLayout.setRefreshing(false);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
