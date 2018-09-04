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
import cn.intersteller.darkintersteller.adapter.InnerFragmentPagerAdapter;

public class SecFragment extends Fragment {
    private String TAG = "SecFragment";
    ViewPager mSecond_viewPager;
    TabLayout msecond_tablayout;
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
        mSecond_viewPager = v.findViewById(R.id.second_viewPager);
        msecond_tablayout = v.findViewById(R.id.second_tablayout);
        addView();
        innerFragmentPagerAdapter = new InnerFragmentPagerAdapter(getChildFragmentManager(), fragments, mTitleList);
        innerFragmentPagerAdapter.notifyDataSetChanged();
        mSecond_viewPager.setAdapter(innerFragmentPagerAdapter);
        msecond_tablayout.setTabMode(TabLayout.MODE_FIXED);
        msecond_tablayout.setupWithViewPager(mSecond_viewPager);

//        int childCount = mSecond_tab.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            TabLayout.Tab tab = mSecond_tab.getTabAt(i);
//            if (tab == null) {
//                return null;
//            }
//            Class c = tab.getClass();
//            try {
//                Field field = c.getDeclaredField("mView");
//                Log.i("deng", "oncloick");
//
//                field.setAccessible(true);
//                final View view = (View) field.get(tab);
//                if (view == null) {
//                    return null;
//                }
//                view.setTag(i);
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.i("deng", "oncloick");
//                    }
//                });
//
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
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
