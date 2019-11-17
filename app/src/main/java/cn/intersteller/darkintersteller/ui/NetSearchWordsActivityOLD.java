package cn.intersteller.darkintersteller.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.innerfragment.SearchSuggestFragment;
import cn.intersteller.darkintersteller.utils.Constant;
import cn.intersteller.darkintersteller.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class NetSearchWordsActivityOLD extends AppCompatActivity implements View.OnTouchListener, SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private InputMethodManager mImm;
    private SearchSuggestFragment mSearchSuggestFragment;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //该界面是顶部toolbar,下面是一个framelayout包裹3个状态的布局:正常搜到结果/加载中/搜索失败
        setContentView(R.layout.activity_net_search);
        toolbar = findViewById(R.id.toolbar);
//        toolbar.setPadding(0, StatusBarUtils.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(toolbar);
        mSearchSuggestFragment = new SearchSuggestFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.search_frame, mSearchSuggestFragment);
        fragmentTransaction.commit();
        mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mSearchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchAll(query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void searchAll(String keyword) {
        HttpUtil.getHttpUtilInstance().sendOkHttpRequest(Constant.NETEASE_SEARCH_SUGGEST + keyword, new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.i("dengsearch", "onFailure");

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.i("dengsearch", Constant.NETEASE_SEARCH + keyword);
                final String responseText = response.body().string();
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(responseText);
                    String resultCode = (String) jsonObject.optString("code");
                    if (!resultCode.equals("200")) {
                        Log.i("dengsearch", "resultCode = " + resultCode);
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(), "搜索失败", Toast.LENGTH_SHORT).show();
//                        mSwipeRefreshLayout.setRefreshing(false);
                        Looper.loop();
                        return;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();//返回箭头结束界面
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hideInputManager();
        return false;
    }

    private void hideInputManager() {
        if (mSearchView != null) {
            if (mImm != null) {
                mImm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
            }
            mSearchView.clearFocus();
//            SearchHistory.getInstance(this).addSearchString(mSearchView.getQuery().toString());
        }
    }

}
