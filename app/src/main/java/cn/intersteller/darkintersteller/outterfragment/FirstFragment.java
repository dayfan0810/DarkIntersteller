package cn.intersteller.darkintersteller.outterfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.AnchorFragment;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.ListFragment;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.RankingFragment;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.RecommendFragment;
import cn.intersteller.darkintersteller.innerfragmnetadapter.InnerFragmentPagerAdapter;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class FirstFragment extends Fragment {
    private String TAG = "FirstFragment";


    @ViewInject(R.id.disco_viewPager)
    private ViewPager disco_viewPager;
    @ViewInject(R.id.disco_tab)
    private TabLayout disco_tab;


    private List<String> mTitleList = new ArrayList<>(4);
    private List<Fragment> fragments = new ArrayList<>(4);
    private RecommendFragment recommendFragment;
    private ListFragment listFragment;
    private AnchorFragment anchorFragment;
    private RankingFragment rankingFragment;
    private View v;
    private static FirstFragment firstFragment;
    private InnerFragmentPagerAdapter innerFragmentPagerAdapter;


    public static FirstFragment newInstance() {
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }
        return firstFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v != null) {
            ViewUtils.inject(this, v);
            return v;
        }
        v = inflater.inflate(R.layout.firstfragment, container, false);
        ViewUtils.inject(this, v);
        addView();
        innerFragmentPagerAdapter = new InnerFragmentPagerAdapter(getFragmentManager(), fragments, mTitleList);
        innerFragmentPagerAdapter.notifyDataSetChanged();
        disco_viewPager.setAdapter(innerFragmentPagerAdapter);
        disco_viewPager.setOffscreenPageLimit(2);
        disco_tab.setTabMode(TabLayout.MODE_FIXED);
        disco_tab.setupWithViewPager(disco_viewPager);
        Log.i(TAG,"onCreateView执行");
        return v;
    }

    private void addView() {
        mTitleList.add("个性推荐");
        mTitleList.add("歌单");
        mTitleList.add("主播电台");
        mTitleList.add("排行榜");
//        disco_tab.addTab(disco_tab.newTab().setText("个性推荐"));
//        disco_tab.addTab(disco_tab.newTab().setText("歌单"));
//        disco_tab.addTab(disco_tab.newTab().setText("主播电台"));
//        disco_tab.addTab(disco_tab.newTab().setText("排行榜"));
        if (recommendFragment == null) {
            recommendFragment = new RecommendFragment();
            fragments.add(recommendFragment);
        }
        if (listFragment == null) {
            listFragment = new ListFragment();
            fragments.add(listFragment);
        }
        if (anchorFragment == null) {
            anchorFragment = new AnchorFragment();
            fragments.add(anchorFragment);
        }
        if (rankingFragment == null) {
            rankingFragment = new RankingFragment();
            fragments.add(rankingFragment);
        }
    }


}
