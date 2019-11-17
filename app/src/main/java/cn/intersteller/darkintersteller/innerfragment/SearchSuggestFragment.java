package cn.intersteller.darkintersteller.innerfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.adapter.SearchSuggestRecyclerViewAdapter;
import cn.intersteller.darkintersteller.bean.SearchSuggestBean;
import cn.intersteller.darkintersteller.outterfragment.FirstFragment;
import cn.intersteller.darkintersteller.utils.Constant;
import cn.intersteller.darkintersteller.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchSuggestFragment extends Fragment implements View.OnClickListener, MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener {
    private ArrayList<SearchSuggestBean.ResultBean.AllMatchBean> mAllMatchBeans = new ArrayList<>();
    private Context mContext;
    private SearchView mSearchView;
    private RecyclerView mReclclerView;
    private SearchSuggestRecyclerViewAdapter searchSuggestRecyclerViewAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.search_suggest_fragment_layout, container, false);
        mReclclerView = layout.findViewById(R.id.rv_search_suggest);
        return layout;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) searchItem.getActionView();
        this.mSearchView.setOnQueryTextListener(this);
        this.mSearchView.setQueryHint("Search");
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search_tips));
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        try {
            Field field = mSearchView.getClass().getDeclaredField("mGoButton");
            field.setAccessible(true);
            ImageView mGoButton = (ImageView) field.get(mSearchView);
            mGoButton.setImageResource(R.drawable.actionbar_search);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        SearchTabPagerFragment fragment = SearchTabPagerFragment.newInstance(0, query);
        FirstFragment firstFragment = FirstFragment.newInstance();
        ft.replace(R.id.search_frame, firstFragment).commitAllowingStateLoss();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            Log.i("dengonQueryTextChange", "newText = " + newText);
            resetSearchRecyclerView();
            return false;
        }
        searchAll(newText);
        return false;
    }

    private void searchAll(String keyword) {
        HttpUtil.getHttpUtilInstance().sendOkHttpRequest(Constant.NETEASE_SEARCH_SUGGEST + keyword + "&type=mobile", new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("dengsearc3", Constant.NETEASE_SEARCH_SUGGEST + keyword + "&type=mobile");
                mAllMatchBeans.clear();
                final String responseText = response.body().string();
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(responseText);
                    String resultCode = (String) jsonObject.optString("code");
                    if (!resultCode.equals("200")) {
                        Looper.prepare();
                        Toast.makeText(mContext, "搜索失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        return;
                    }

                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONArray allMatch = result.optJSONArray("allMatch");
                    if (allMatch == null || result == null) {
                        getActivity().runOnUiThread(() -> {
                            LinearLayoutManager manager = new LinearLayoutManager(mContext);
                            mReclclerView.setLayoutManager(manager);
                            searchSuggestRecyclerViewAdapter = new SearchSuggestRecyclerViewAdapter(mContext, mAllMatchBeans, mReclclerView, manager);
                            mReclclerView.setAdapter(searchSuggestRecyclerViewAdapter);
                        });

                        return;
                    }
                    for (int i = 0; i < allMatch.length(); i++) {
                        JSONObject allMatchItem = (JSONObject) allMatch.get(i);
                        String keyword_suggest = allMatchItem.optString("keyword");
                        SearchSuggestBean.ResultBean.AllMatchBean allMatchBean = new SearchSuggestBean.ResultBean.AllMatchBean();
                        allMatchBean.setKeyword(keyword_suggest);
                        mAllMatchBeans.add(allMatchBean);
                    }
                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread(() -> {
                        LinearLayoutManager manager = new LinearLayoutManager(mContext);
                        mReclclerView.setLayoutManager(manager);
                        searchSuggestRecyclerViewAdapter = new SearchSuggestRecyclerViewAdapter(mContext, mAllMatchBeans, mReclclerView, manager);
                        Log.i("dengsearch3", "mAllMatchBeans size = " + mAllMatchBeans.size());
                        searchSuggestRecyclerViewAdapter.setmOnItemClickListener(new SearchSuggestRecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                if (mAllMatchBeans.size() <= 0) {
                                    Log.i("deng", "onItemClick no data, return");
                                    return;
                                }

                                SearchSuggestBean.ResultBean.AllMatchBean item = searchSuggestRecyclerViewAdapter.getItem(position);
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                SearchTabPagerFragment fragment = SearchTabPagerFragment.newInstance(0, item.getKeyword());
                                ft.replace(R.id.search_frame, fragment).commitAllowingStateLoss();
                            }
                        });
                        mReclclerView.setAdapter(searchSuggestRecyclerViewAdapter);


                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }


    public void resetSearchRecyclerView() {
        //每当输入的text改变时, 就重置adapter
        mAllMatchBeans.clear();
        mReclclerView.setAdapter(searchSuggestRecyclerViewAdapter);
    }


}
