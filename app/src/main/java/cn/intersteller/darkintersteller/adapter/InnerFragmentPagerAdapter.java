package cn.intersteller.darkintersteller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by Day on 2017/1/4.
 */

public class InnerFragmentPagerAdapter extends FragmentPagerAdapter {
    public List<Fragment> fragments;
    public List<String> tiles;

    public InnerFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tiles) {
        super(fm);
        this.fragments = fragments;
        this.tiles = tiles;
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
        return tiles.get(position);
    }
}
