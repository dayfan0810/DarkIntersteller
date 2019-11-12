package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.adapter.MyPlayListRecyclerViewAdapter;
import cn.intersteller.darkintersteller.bean.MyPlayListBean;
import cn.intersteller.darkintersteller.ui.MyPlayListDetailActivity;
import cn.intersteller.darkintersteller.utils.Constant;
import cn.intersteller.darkintersteller.utils.HttpUtil;
import cn.intersteller.darkintersteller.utils.SharedPreferenceUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static cn.intersteller.darkintersteller.utils.Constant.LOGIN_USER_ID;

public class MyPlaylistFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View myplaylistfragment;
    private RecyclerView myplaylist_fragment_recyclerview;
    private SwipeRefreshLayout myplaylist_fragment_swiperefresh;
    List<MyPlayListBean.PlaylistBean> mPlaylistBeans = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myplaylistfragment = inflater.inflate(R.layout.myplaylistfragment, container, false);
        myplaylist_fragment_recyclerview = myplaylistfragment.findViewById(R.id.myplaylist_fragment_recyclerview);
        myplaylist_fragment_swiperefresh = myplaylistfragment.findViewById(R.id.myplaylist_fragment_swiperefresh);
        myplaylist_fragment_swiperefresh.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        myplaylist_fragment_swiperefresh.setOnRefreshListener(this);
        onRefresh();
        return myplaylistfragment;
    }

    @Override
    public void onRefresh() {
        mPlaylistBeans.clear();
        requestCloudPanMusic();
    }

    public void requestCloudPanMusic() {
        long pref = SharedPreferenceUtils.getPrefLong(LOGIN_USER_ID, (long) 0);
        HttpUtil.getInstance().sendOkHttpRequest(Constant.NETEASE_MYPLAYLIST + pref, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    String resultCode = (String) jsonObject.optString("code");
                    if (resultCode.equals("301")) {
                        getActivity().runOnUiThread(() -> {
                            Toast.makeText(getActivity(), "还没登陆，请先登录", Toast.LENGTH_LONG).show();
                            myplaylist_fragment_swiperefresh.setRefreshing(false);
                        });
                        return;
                    }

                    JSONArray playListdata = jsonObject.getJSONArray("playlist");
                    for (int i = 0; i < playListdata.length(); i++) {
                        JSONObject playListdataItem = (JSONObject) playListdata.get(i);

                        String playlistName = playListdataItem.optString("name");
                        long playlistId = playListdataItem.optLong("id");
                        int playlistTrackCount = playListdataItem.optInt("trackCount");

                        String coverImgUrl = playListdataItem.optString("coverImgUrl");

                        MyPlayListBean.PlaylistBean playlistBean = new MyPlayListBean.PlaylistBean();
                        playlistBean.setName(playlistName);
                        playlistBean.setTrackCount(playlistTrackCount);
                        playlistBean.setId(playlistId);
                        playlistBean.setCoverImgUrl(coverImgUrl);
                        mPlaylistBeans.add(playlistBean);
                    }

                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        MyPlayListRecyclerViewAdapter myPlayListRecyclerViewAdapter;

                        @Override
                        public void run() {
                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            myplaylist_fragment_recyclerview.setLayoutManager(manager);

                            myPlayListRecyclerViewAdapter = new MyPlayListRecyclerViewAdapter(getContext(), mPlaylistBeans, myplaylist_fragment_recyclerview, manager);
                            myplaylist_fragment_recyclerview.setAdapter(myPlayListRecyclerViewAdapter);
                            myplaylist_fragment_swiperefresh.setRefreshing(false);
                            myPlayListRecyclerViewAdapter.setmOnItemClickListener(new MyPlayListRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    MyPlayListBean.PlaylistBean playlistBean = myPlayListRecyclerViewAdapter.getItem(position);
//                                    String name = playlistBean.getName();
//              　                      String coverImgUrl = playlistBean.getCoverImgUrl();
//                                    int cloudTrackCount = playlistBean.getCloudTrackCount();
                                    Intent intent = new Intent(getActivity(), MyPlayListDetailActivity.class);
//                                    intent.putExtra("newsItem", playlistBean);
                                    startActivity(intent);
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });
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
