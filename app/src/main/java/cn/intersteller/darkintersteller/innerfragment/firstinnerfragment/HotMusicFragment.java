package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.show.api.ShowApiRequest;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.HotMusicBean;
import cn.intersteller.darkintersteller.utils.Constant;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class HotMusicFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Banner mBanner;
    private List<HotMusicBean> mHotMusicBeanList = new ArrayList<>();
    private String hot_qq_music_request;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotmusicfragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.hotmusic_swipeRefreshLayout);
        mBanner = view.findViewById(R.id.top_news_banner);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.top_news_recyclerView);
        onRefresh();
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        mHotMusicBeanList.clear();
        requestHotMusic();
    }

    private void requestHotMusic() {
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "xxx";//要替换成自己的
                String secret = "xxxxxxx";//要替换成自己的
                hot_qq_music_request = new ShowApiRequest("http://route.showapi.com/213-4", Constant.APPID_QQ_MUSIC_HOT, Constant.SECRET_QQ_MUSIC_HOT)
                        .addTextPara("topid", "5")
                        .post();
                System.out.println("deng" + hot_qq_music_request);
            }
        }.start();

    }
}
