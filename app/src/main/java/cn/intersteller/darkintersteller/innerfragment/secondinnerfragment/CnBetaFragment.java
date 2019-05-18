package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.content.Intent;
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
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.WebGrabber.CNBETA.CnbetaNewsGrabber;
import cn.intersteller.darkintersteller.adapter.CnbetaNewsRecyclerViewAdapter;
import cn.intersteller.darkintersteller.bean.CnbetaNewsBean;
import cn.intersteller.darkintersteller.ui.CnbetaNewsDetailActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static cn.intersteller.darkintersteller.utils.HttpUtil.makeRequest;


public class CnBetaFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    CnbetaNewsRecyclerViewAdapter newsAdapter;
    private int firstPage = 1;
    private int count = 0;
    List<CnbetaNewsBean> mCnbetaNewsBeanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCnbetaNewsBeanList = new ArrayList<>();
        view = inflater.inflate(R.layout.cnbeta_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.cnbeta_fragment_swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = view.findViewById(R.id.cnbeta_fragment_recyclerView);
        mRecyclerView.addOnScrollListener(monScrollListener);
        mRecyclerView.setHasFixedSize(true);

        onRefresh();
        return view;
    }

    private int mLastVisibleItemPosition = -1;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (newsAdapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == newsAdapter.getItemCount()) {
                    //发送网络请求获取更多数据
                    Log.i("deng2222", "!isLoadingData = ");

                    sendMoreRequest();
//                    if (!recyclerView.canScrollVertically(1)) {
//                    }
                }
            }
        }

        private void sendMoreRequest() {
            OkHttpClient client = new OkHttpClient();
            count++;
            final Call call = client.newCall(makeRequest(firstPage + count));
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    Looper.prepare();
                    Toast.makeText(getContext(), "获取Cnbeta新闻信息失败", Toast.LENGTH_SHORT).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                    Looper.loop();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
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
                        Log.i("deng333", "list112 =  " + list);
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject dataItem = (JSONObject) list.get(i);
                            String title = dataItem.optString("title");
                            String hometext = dataItem.optString("hometext");
                            int mview = dataItem.optInt("mview");
                            String inputtime = dataItem.optString("inputtime");
                            String thumb = dataItem.optString("thumb");
                            String url_show = dataItem.optString("url_show");
                            int comments = dataItem.optInt("comments");
                            CnbetaNewsBean newsBean = new CnbetaNewsBean();
                            newsBean.setTitle(title);
                            newsBean.setHometext(hometext);
                            newsBean.setMview(mview);
                            newsBean.setInputtime(inputtime);
                            newsBean.setThumb(thumb);
                            newsBean.setUrl_show(url_show);
                            newsBean.setComments(comments);
//                            Log.i("deng1111", "comments =  " + comments);
                            mCnbetaNewsBeanList.add(newsBean);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "加载第" + (count) + "页", Toast.LENGTH_SHORT).show();
                                Log.i("deng2222", "count =  " + count);
//                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
//                                mRecyclerView.setLayoutManager(manager);
                                Log.i("deng2222", "mNewsBeanList.size =  " + mCnbetaNewsBeanList.size());
                                int positionStart = newsAdapter.getItemCount();
                                Log.i("deng2222", "positionStart =  " + positionStart);

//                                newsAdapter.addAll(mCnbetaNewsBeanList);
//                                newsAdapter.notifyItemRangeInserted(positionStart, mCnbetaNewsBeanList.size());
                                newsAdapter.notifyDataSetChanged();
//                                newsAdapter.setmOnItemClickListener(new CnbetaNewsRecyclerViewAdapter.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(View view, int position) {
//                                        if (mCnbetaNewsBeanList.size() <= 0) {
//                                            return;
//                                        }
//                                        Log.i("deng2222", "onItemClick =  ");
//                                        CnbetaNewsBean item = newsAdapter.getItem(position);
//                                        View transitionView = view.findViewById(R.id.cnbeta_news_item_icon);
//                                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
//                                        intent.putExtra("newsItem", item);
//                                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                                                transitionView, getString(R.string.transition_news_img));
//                                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
//                                    }
//
//                                    @Override
//                                    public void onItemLongClick(View view, int position) {
//
//
//                                    }
//                                });
//                                mRecyclerView.setAdapter(newsAdapter);
                                mSwipeRefreshLayout.setRefreshing(false);

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

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
        OkHttpClient client = new OkHttpClient();
        final Call call = client.newCall(makeRequest(firstPage));
        call.enqueue(new Callback() {
            //            List<CnbetaNewsBean> mCnbetaNewsBeanList = new ArrayList<>();
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
                Log.i("deng111", "responseText =  " + responseText);
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
                    Log.i("deng333", "list111 =  " + list);
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject dataItem = (JSONObject) list.get(i);
                        String title = dataItem.optString("title");
                        String hometext = dataItem.optString("hometext");
                        int mview = dataItem.optInt("mview");
                        String inputtime = dataItem.optString("inputtime");
                        String thumb = dataItem.optString("thumb");
                        String url_show = dataItem.optString("url_show");
                        int comments = dataItem.optInt("comments");
                        CnbetaNewsBean newsBean = new CnbetaNewsBean();
                        newsBean.setTitle(title);
                        newsBean.setHometext(hometext);
                        newsBean.setMview(mview);
                        newsBean.setInputtime(inputtime);
                        newsBean.setThumb(thumb);
                        newsBean.setUrl_show(url_show);
                        newsBean.setComments(comments);
//                        Log.i("deng1111", "comments =  " + comments);
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
                                    CnbetaNewsGrabber mCnbetaNewsGrabber = new CnbetaNewsGrabber();
                                    ArrayList cnbetaNewsBeanByCallable = mCnbetaNewsGrabber.getCnbetaNewsBeanByCallable();

                                    Intent intent = new Intent(getActivity(), CnbetaNewsDetailActivity.class);
                                    intent.putExtra("newsItem", item);
                                    startActivity(intent);

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


    @Override
    public void onStop() {
        super.onStop();
        count = 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
