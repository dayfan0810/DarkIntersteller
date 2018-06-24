package cn.intersteller.darkintersteller.innerfragmnetadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
