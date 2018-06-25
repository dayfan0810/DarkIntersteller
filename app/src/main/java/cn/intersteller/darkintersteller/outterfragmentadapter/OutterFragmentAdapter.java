package cn.intersteller.darkintersteller.outterfragmentadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Day on 2017/1/4.
 */

public class OutterFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public OutterFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        Log.i("deng","OutterFragmentAdapter mFragmentList.get(position) == null ? "+(mFragmentList.get(position)==null));
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
