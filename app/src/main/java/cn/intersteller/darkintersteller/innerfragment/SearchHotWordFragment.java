package cn.intersteller.darkintersteller.innerfragment;

import android.app.Activity;
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
import cn.intersteller.darkintersteller.bean.SearchInfoBean;

public class SearchHotWordFragment extends Fragment implements View.OnClickListener {
    String[] texts = new String[10];
    ArrayList<TextView> views = new ArrayList<>();
    SearchWords searchWords;
//    private RecentSearchAdapter adapter;
    private RecyclerView recyclerView;
    private boolean isFromCache = true;

    private List searchResults = Collections.emptyList();
    private ArrayList<SearchInfoBean> songList = new ArrayList<>();
    View loadview;
    FrameLayout frameLayout;

    public Activity mContext;

    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.mContext = activity;
    }



    public interface SearchWords {
        void onSearch(String t);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.load_framelayout, container, false);
        frameLayout = (FrameLayout) view.findViewById(R.id.loadframe);
        loadview = LayoutInflater.from(mContext).inflate(R.layout.loading, frameLayout, false);
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

//    private void loadWords() {
//        new AsyncTask<Boolean, Void, Boolean>() {
//
//            @Override
//            protected Boolean doInBackground(Boolean... params) {
//                if (NetworkUtils.isConnectInternet(mContext)) {
//                    isFromCache = false;
//                }
//
//
//                try {
////                    JsonArray jsonArray = HttpUtil.getResposeJsonObject(BMA.Search.hotWord(), mContext, isFromCache).get("result").getAsJsonArray();
////                    for (int i = 0; i < 10; i++) {
////                        texts[i] = jsonArray.get(i).getAsJsonObject().get("word").getAsString();
////                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return false;
//                }
//
//                return true;
//            }
//
//            @Override
//            protected void onPostExecute(Boolean load) {
//                super.onPostExecute(load);
//                if (!load && mContext == null) {
//
//                    return;
//                }
////                View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_search_hot_words, frameLayout, false);
////                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//                recyclerView.setHasFixedSize(true);
////                adapter = new RecentSearchAdapter(mContext);
////                adapter.setListenter(SearchHotWordFragment.this);
////                recyclerView.setAdapter(adapter);
////
////                TextView text1 = (TextView) view.findViewById(R.id.text1);
////                TextView text2 = (TextView) view.findViewById(R.id.text2);
////                TextView text3 = (TextView) view.findViewById(R.id.text3);
////                TextView text4 = (TextView) view.findViewById(R.id.text4);
////                TextView text5 = (TextView) view.findViewById(R.id.text5);
////                TextView text6 = (TextView) view.findViewById(R.id.text6);
////                TextView text7 = (TextView) view.findViewById(R.id.text7);
////                TextView text8 = (TextView) view.findViewById(R.id.text8);
////                TextView text9 = (TextView) view.findViewById(R.id.text9);
////                TextView text10 = (TextView) view.findViewById(R.id.text10);
////                views.add(text1);
////                views.add(text2);
////                views.add(text3);
////                views.add(text4);
////                views.add(text5);
////                views.add(text6);
////                views.add(text7);
////                views.add(text8);
////                views.add(text9);
////                views.add(text10);
//
//
//                frameLayout.removeAllViews();
////                frameLayout.addView(view);
//
//                int w = mContext.getResources().getDisplayMetrics().widthPixels;
//                int xdistance = -1;
//                int ydistance = 0;
//                int distance = dip2px(mContext, 16);
//            }
//        }.execute();

//    }

    public void searchWords(SearchWords searchWords) {
        this.searchWords = searchWords;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.text1:
//                searchWords.onSearch(texts[0]);
//                break;
//            case R.id.text2:
//                searchWords.onSearch(texts[1]);
//                break;
//            case R.id.text3:
//                searchWords.onSearch(texts[2]);
//                break;
//            case R.id.text4:
//                searchWords.onSearch(texts[3]);
//                break;
//            case R.id.text5:
//                searchWords.onSearch(texts[4]);
//                break;
//            case R.id.text6:
//                searchWords.onSearch(texts[5]);
//                break;
//            case R.id.text7:
//                searchWords.onSearch(texts[6]);
//                break;
//            case R.id.text8:
//                searchWords.onSearch(texts[7]);
//                break;
//            case R.id.text9:
//                searchWords.onSearch(texts[8]);
//                break;
//            case R.id.text10:
//                searchWords.onSearch(texts[9]);
//                break;
        }
    }

//    @Override
//    public void onSearch(String t) {
//        if (searchWords != null)
//            searchWords.onSearch(t);
//    }
}
