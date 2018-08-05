package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.NewsBean;
import cn.intersteller.darkintersteller.utils.Constant;
import cn.intersteller.darkintersteller.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class NewsFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Banner mBanner;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.top_news_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.top_news_swipeRefreshLayout);
        mBanner = view.findViewById(R.id.top_news_banner);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.top_news_recyclerView);
        NewsAdapter newsAdapter = new NewsAdapter(getContext(), mNewsBeanList);
        mRecyclerView.setAdapter(newsAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        requestNews();
    }


    public void requestNews() {
        HttpUtil.sendOkHttpRequest(Constant.URL_TOPNEWS, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    String resultCode = (String) jsonObject.optString("reason");
                    if (!resultCode.equals("成功的返回")) {
                        return;
                    }
                    JSONObject jsonObject_result = jsonObject.getJSONObject("result");
                    JSONArray data = jsonObject_result.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject dataItem = (JSONObject) data.get(i);
                        String title = dataItem.optString("title");
                        String thumbnail_pic_s = dataItem.optString("thumbnail_pic_s");
                        String date = dataItem.optString("date");
                        NewsBean newsBean = new NewsBean();
                        newsBean.setNewsTitle(title);
                        newsBean.setNewsIconUrl(thumbnail_pic_s);
                        mNewsBeanList.add(newsBean);
                    }

                    Log.i("deng", "mNewsBeanList.size = " + mNewsBeanList.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "获取新闻信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });

    }

}
