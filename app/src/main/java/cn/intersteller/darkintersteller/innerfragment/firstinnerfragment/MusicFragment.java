package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.intersteller.darkintersteller.R;

public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View mMusicfragment_layout;
    private RecyclerView mMusic_fragment_recyclerview;
    private SwipeRefreshLayout mMusic_fragment_swiperefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMusicfragment_layout = inflater.inflate(R.layout.musicfragment, container, false);
        mMusic_fragment_recyclerview = mMusicfragment_layout.findViewById(R.id.music_fragment_recyclerview);
        mMusic_fragment_swiperefresh = mMusicfragment_layout.findViewById(R.id.music_fragment_swiperefresh);
        mMusic_fragment_swiperefresh.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mMusic_fragment_swiperefresh.setOnRefreshListener(this);
        return mMusicfragment_layout;
    }

    @Override
    public void onRefresh() {

    }
}
