package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.intersteller.darkintersteller.R;

public class CloudPanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View mCloudpanfragment_layout;
    private RecyclerView mCloudpan_fragment_recyclerview;
    private SwipeRefreshLayout mCloudpan_fragment_swiperefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCloudpanfragment_layout = inflater.inflate(R.layout.cloudpanfragment, container, false);
        mCloudpan_fragment_recyclerview = mCloudpanfragment_layout.findViewById(R.id.cloudpan_fragment_recyclerview);
        mCloudpan_fragment_swiperefresh = mCloudpanfragment_layout.findViewById(R.id.cloudpan_fragment_swiperefresh);
        mCloudpan_fragment_swiperefresh.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mCloudpan_fragment_swiperefresh.setOnRefreshListener(this);
        return mCloudpanfragment_layout;
    }

    @Override
    public void onRefresh() {

    }
}
