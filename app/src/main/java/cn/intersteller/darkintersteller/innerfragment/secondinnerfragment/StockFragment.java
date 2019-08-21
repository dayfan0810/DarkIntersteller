package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.WebGrabber.USHK.USHKNewsBean;
import cn.intersteller.darkintersteller.WebGrabber.USHK.UshkNewsGrabber;
import cn.intersteller.darkintersteller.adapter.Jin10RecyclerViewAdapter;


public class StockFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private UshkNewsGrabber mUshkNewsGrabber = new UshkNewsGrabber();
    //    private CnbetaNewsGrabber mCnbetaNewsGrabber = new CnbetaNewsGrabber();
    private Jin10RecyclerViewAdapter newsAdapter;

    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<USHKNewsBean> mNewsBeanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stock_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.stock_fragment_swipeRefreshLayout);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.stock_fragment_recyclerView);
//        onRefresh();
        return view;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mNewsBeanList.clear();
        requestNews();
    }


    public void requestNews() {
        //美港电讯用的是jsoup
        //1.USHKNEWS
//        ArrayList ushkNewsBeanByCallable = mUshkNewsGrabber.getUSHKNewsBeanDerectely();
        ArrayList ushkNewsBeanByCallable = mUshkNewsGrabber.getUSHKNewsBeanByCallable();
        if (ushkNewsBeanByCallable == null) {
            return;
        }
        for (int i = 0; i < ushkNewsBeanByCallable.size(); i++) {
            USHKNewsBean uSHKNewsBean = (USHKNewsBean) ushkNewsBeanByCallable.get(i);
            String text = uSHKNewsBean.getText();
            String time = uSHKNewsBean.getTime();
            mNewsBeanList.add(uSHKNewsBean);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);
                newsAdapter = new Jin10RecyclerViewAdapter(getContext(), mNewsBeanList, mRecyclerView, manager);
                newsAdapter.setmOnItemClickListener(new Jin10RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.i("deng", "onItemClick ");
                        if (mNewsBeanList.size() <= 0) {
                            Log.i("deng", "onItemClick no data, return");
                            return;
                        }

                        USHKNewsBean item = newsAdapter.getItem(position);

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Log.i("deng", "onItemLongClick ");


                    }
                });
                newsAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(newsAdapter);
//                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
//        ushkNewsBeanByCallable.clear();
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
