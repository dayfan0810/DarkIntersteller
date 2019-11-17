package cn.intersteller.darkintersteller.innerfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.intersteller.darkintersteller.R;

public class SearchHotWordFragmentOLD extends Fragment implements View.OnClickListener {
    String[] texts = new String[10];
    ArrayList<TextView> views = new ArrayList<>();
    //    private RecentSearchAdapter adapter;
    private RecyclerView recyclerView;

    private List searchResults = Collections.emptyList();
    //    private ArrayList<SearchInfoBean> songList = new ArrayList<>();
    View loadview;
    FrameLayout frameLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_suggest_fragment_layout, container, false);
//        frameLayout = view.findViewById(R.id.loadframe);
        loadview = LayoutInflater.from(getContext()).inflate(R.layout.include_loading_hard, frameLayout, false);
        frameLayout.addView(loadview);
//        loadWords();

        return view;
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
