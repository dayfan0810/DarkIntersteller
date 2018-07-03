package cn.intersteller.darkintersteller.outterfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.innerfragment.secondinnerfragment.NewsFragment;
import cn.intersteller.darkintersteller.innerfragment.secondinnerfragment.StockFragment;
import cn.intersteller.darkintersteller.innerfragmnetadapter.InnerFragmentPagerAdapter;

public class SecFragment extends Fragment {
    private String TAG = "SecFragment";
    ViewPager mMusic_viewPager;
    TabLayout mMusic_tab;
    private List<String> mTitleList = new ArrayList<>(2);
    private List<Fragment> fragments = new ArrayList<>(2);
    private View v;
    private static SecFragment secFragment;
    private InnerFragmentPagerAdapter innerFragmentPagerAdapter;
    NewsFragment newsFragment;
    StockFragment stockFragment;

    public static SecFragment newInstance() {
        if (secFragment == null) {
            secFragment = new SecFragment();
        }
        return secFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.secondfragment, container, false);
        mMusic_viewPager = v.findViewById(R.id.music_viewPager);
        mMusic_tab = v.findViewById(R.id.music_tab);
        addView();
        innerFragmentPagerAdapter = new InnerFragmentPagerAdapter(getChildFragmentManager(), fragments, mTitleList);
        innerFragmentPagerAdapter.notifyDataSetChanged();
        mMusic_viewPager.setAdapter(innerFragmentPagerAdapter);
        mMusic_tab.setTabMode(TabLayout.MODE_FIXED);
        mMusic_tab.setupWithViewPager(mMusic_viewPager);
        Log.i(TAG, "onCreateView执行");
        return v;
    }

    private void addView() {
        mTitleList.add("新闻推荐");
        mTitleList.add("股票");
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
            fragments.add(newsFragment);
        }
        if (stockFragment == null) {
            stockFragment = new StockFragment();
            fragments.add(stockFragment);
        }
    }
}
