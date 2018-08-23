package cn.intersteller.darkintersteller.outterfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.AnchorFragment;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.ListFragment;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.HotMusicFragment;
import cn.intersteller.darkintersteller.innerfragment.firstinnerfragment.RecommendFragment;

public class FirstFragment extends Fragment {
    private String TAG = "FirstFragment";


    private ViewPager mDisco_viewPager;
    private TabLayout mDisco_tab;


    private List<String> mTitleList = new ArrayList<>(4);
    private List<Fragment> fragments = new ArrayList<>(4);
    private RecommendFragment recommendFragment;
    private ListFragment listFragment;
    private AnchorFragment anchorFragment;
    private HotMusicFragment hotMusicFragment;
    private View v;
    private static FirstFragment firstFragment;


    public static FirstFragment newInstance() {
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }
        return firstFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.firstfragment, container, false);
        mDisco_tab =  v.findViewById(R.id.disco_tab);
        mDisco_viewPager = v.findViewById(R.id.disco_viewPager);
        addView();
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager());
        myAdapter.notifyDataSetChanged();
        mDisco_viewPager.setAdapter(myAdapter);
        mDisco_viewPager.setOffscreenPageLimit(2);
        mDisco_tab.setTabMode(TabLayout.MODE_FIXED);
        mDisco_tab.setupWithViewPager(mDisco_viewPager);
        Log.i(TAG,"onCreateView执行");
        return v;
    }

    private void addView() {
        mTitleList.add("个性推荐");
        mTitleList.add("歌单");
        mTitleList.add("主播电台");
        mTitleList.add("QQ热榜");
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
        if (hotMusicFragment == null) {
            hotMusicFragment = new HotMusicFragment();
            fragments.add(hotMusicFragment);
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }


}
