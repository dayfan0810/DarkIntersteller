package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.content.Intent;
import android.os.Bundle;
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
import cn.intersteller.darkintersteller.adapter.CloudPanRecyclerViewAdapter;
import cn.intersteller.darkintersteller.bean.CloudPanBean;
import cn.intersteller.darkintersteller.login.LoginActivity;
import cn.intersteller.darkintersteller.ui.MvDetailActivity;
import cn.intersteller.darkintersteller.utils.Constant;
import cn.intersteller.darkintersteller.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CloudPanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View mCloudpanfragment_layout;
    private RecyclerView mCloudpan_fragment_recyclerview;
    private SwipeRefreshLayout mCloudpan_fragment_swiperefresh;
    List<CloudPanBean.DataBean> mCloudPanBeans = new ArrayList<>();
    List<CloudPanBean.DataBean.SimpleSongBean> mSimpleSongBeans = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCloudpanfragment_layout = inflater.inflate(R.layout.cloudpanfragment, container, false);
        mCloudpan_fragment_recyclerview = mCloudpanfragment_layout.findViewById(R.id.cloudpan_fragment_recyclerview);
        mCloudpan_fragment_swiperefresh = mCloudpanfragment_layout.findViewById(R.id.cloudpan_fragment_swiperefresh);
        mCloudpan_fragment_swiperefresh.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mCloudpan_fragment_swiperefresh.setOnRefreshListener(this);
        onRefresh();
        return mCloudpanfragment_layout;
    }

    @Override
    public void onRefresh() {
        mCloudPanBeans.clear();
        mSimpleSongBeans.clear();
        requestCloudPanMusic();
    }

    public void requestCloudPanMusic() {
        HttpUtil.getInstance().sendOkHttpRequest(Constant.NETEASE_CLOUD_PAN, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();

                Log.i("deng-pan", "responseText = " + responseText);
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    String resultCode = (String) jsonObject.optString("code");
                    if (resultCode.equals("301")) {
                        getActivity().runOnUiThread(() -> {
                            Toast.makeText(getActivity(), "还没登陆，请先登录", Toast.LENGTH_LONG).show();
                            mCloudpan_fragment_swiperefresh.setRefreshing(false);
                        });
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        return;
                    }

                    JSONArray cloudPandata = jsonObject.getJSONArray("data");
                    for (int i = 0; i < cloudPandata.length(); i++) {
                        JSONObject cloudPanItem = (JSONObject) cloudPandata.get(i);

                        long songId = cloudPanItem.optLong("songId");//Id
                        String album = cloudPanItem.optString("album");//专辑名
                        String artist = cloudPanItem.optString("artist");//歌手
                        String songName = cloudPanItem.optString("songName");//歌名

                        CloudPanBean.DataBean dataBean = new CloudPanBean.DataBean();
//                        Log.i("dengpan", "songId  = " + songId);
//                        Log.i("dengpan", "album  = " + album);
//                        Log.i("dengpan", "artist  = " + artist);
//                        Log.i("dengpan", "songName  = " + songName);
                        dataBean.setAlbum(album);
                        dataBean.setSongId(songId);
                        dataBean.setArtist(artist);
                        dataBean.setSongName(songName);
                        mCloudPanBeans.add(dataBean);
                        JSONObject simpleSong = cloudPanItem.getJSONObject("simpleSong");
                        CloudPanBean.DataBean.SimpleSongBean simpleSongBean = new CloudPanBean.DataBean.SimpleSongBean();
                        JSONObject al = simpleSong.getJSONObject("al");
                        String name = al.optString("name");
                        simpleSongBean.setName(name);
                        mSimpleSongBeans.add(simpleSongBean);
                    }

                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {

                        private CloudPanRecyclerViewAdapter cloudPanRecyclerViewAdapter;

                        @Override
                        public void run() {
                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            mCloudpan_fragment_recyclerview.setLayoutManager(manager);
                            cloudPanRecyclerViewAdapter = new CloudPanRecyclerViewAdapter(getContext(), mCloudPanBeans,mSimpleSongBeans, mCloudpan_fragment_recyclerview, manager);
                            cloudPanRecyclerViewAdapter.setmOnItemClickListener(new CloudPanRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Log.i("deng", "onItemClick ");
                                    if (mCloudPanBeans.size() <= 0) {
                                        Log.i("deng", "onItemClick no data, return");
                                        return;
                                    }

                                    CloudPanBean.DataBean item = cloudPanRecyclerViewAdapter.getItem(position);
                                    Intent intent = new Intent(getActivity(), MvDetailActivity.class);
                                    Bundle mBundle = new Bundle();
                                    mBundle.putSerializable("mvitem", item);
                                    intent.putExtras(mBundle);
                                    startActivity(intent);

                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });
                            mCloudpan_fragment_recyclerview.setAdapter(cloudPanRecyclerViewAdapter);
                            mCloudpan_fragment_swiperefresh.setRefreshing(false);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }
}
