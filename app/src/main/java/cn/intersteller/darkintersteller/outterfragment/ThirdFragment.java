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
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.BubbleSortFragment;
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.HeapSortFragment;
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.InsertSortFragment;
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.MeargeSortFragment;
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.QuickSortFragment;
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.SelectSortFragment;
import cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment.ShellSortFragment;
import cn.intersteller.darkintersteller.innerfragmnetadapter.InnerFragmentPagerAdapter;

/**
 * Created by Day on 2017/1/2.
 */

public class ThirdFragment extends BaseFragment {
    private String TAG = "ThirdFragment";
    private static ThirdFragment thirdFragment;
    private View v;
    private InnerFragmentPagerAdapter innerFragmentPagerAdapter;
    private List<String> mTitleList = new ArrayList<>(7);
    private List<Fragment> fragments = new ArrayList<>(7);
    private QuickSortFragment quickSortFragment;
    private BubbleSortFragment bubbleSortFragment;
    private HeapSortFragment heapSortFragment;
    private MeargeSortFragment meargeSortFragment;
    private InsertSortFragment insertSortFragment;
    private SelectSortFragment selectSortFragment;
    private ShellSortFragment shellSortFragment;
    TabLayout mFrirendsTabLayout;
    ViewPager mFiendsViewPager;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public static ThirdFragment newInstance() {
        if (thirdFragment != null) {
            thirdFragment = new ThirdFragment();
        }
        return thirdFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.thirdfragment, container, false);
        mFrirendsTabLayout = v.findViewById(R.id.friends_tab);
        mFiendsViewPager = v.findViewById(R.id.firends_viewPager);
        addView();
        innerFragmentPagerAdapter = new InnerFragmentPagerAdapter(getFragmentManager(), fragments, mTitleList);
        innerFragmentPagerAdapter.notifyDataSetChanged();
        mFrirendsTabLayout = v.findViewById(R.id.friends_tab);
        mFiendsViewPager.setAdapter(innerFragmentPagerAdapter);
        mFiendsViewPager.setOffscreenPageLimit(2);
        mFrirendsTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mFrirendsTabLayout.setupWithViewPager(mFiendsViewPager);
        mFrirendsTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        Log.i(TAG,"onCreateView执行");
        return v;
    }

    private void addView() {
        mTitleList.add("快速排序");
        mTitleList.add("并归排序");
        mTitleList.add("希尔排序");
        mTitleList.add("堆排序");
        mTitleList.add("冒泡排序");
        mTitleList.add("选择排序");
        mTitleList.add("插入排序");
        if (quickSortFragment == null) {
            quickSortFragment = new QuickSortFragment();
            fragments.add(quickSortFragment);
        }
        if (shellSortFragment == null) {
            shellSortFragment = new ShellSortFragment();
            fragments.add(shellSortFragment);
        }
        if (selectSortFragment == null) {
            selectSortFragment = new SelectSortFragment();
            fragments.add(shellSortFragment);
        }
        if (heapSortFragment == null) {
            heapSortFragment = new HeapSortFragment();
            fragments.add(heapSortFragment);
        }
        if (bubbleSortFragment == null) {
            bubbleSortFragment = new BubbleSortFragment();
            fragments.add(bubbleSortFragment);
        }
        if (meargeSortFragment == null) {
            meargeSortFragment = new MeargeSortFragment();
            fragments.add(meargeSortFragment);
        }
        if (insertSortFragment == null) {
            insertSortFragment = new InsertSortFragment();
            fragments.add(insertSortFragment);
        }
    }
}
