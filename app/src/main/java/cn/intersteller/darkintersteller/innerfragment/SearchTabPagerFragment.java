package cn.intersteller.darkintersteller.innerfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.intersteller.darkintersteller.bean.SearchSingleSongBean;
import cn.intersteller.darkintersteller.bean.SearchSuggestBean;
import cn.intersteller.darkintersteller.bean.SearchVideoBean;

public class SearchTabPagerFragment extends Fragment {

    private ViewPager viewPager;
    private int page = 0;
    String key;
    private List searchResults = Collections.emptyList();
    FrameLayout frameLayout;
    View contentView;
    ArrayList<SearchSuggestBean> mSearchSuggestBean = new ArrayList<>();
    ArrayList<SearchSingleSongBean> mSearchSingleSongBean = new ArrayList<>();
    ArrayList<SearchVideoBean> mSearchVideoBean = new ArrayList<>();

    public static final SearchTabPagerFragment newInstance(int page, String key) {
        SearchTabPagerFragment f = new SearchTabPagerFragment();
        Bundle bdl = new Bundle(1);
        bdl.putInt("page_number", page);
        bdl.putString("key", key);
        f.setArguments(bdl);
        return f;
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

