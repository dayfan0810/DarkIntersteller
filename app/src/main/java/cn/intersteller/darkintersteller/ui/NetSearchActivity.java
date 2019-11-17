package cn.intersteller.darkintersteller.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.innerfragment.SearchSuggestFragment;


public class NetSearchActivity extends AppCompatActivity {

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        mSearchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        mSearchView.setMaxWidth(Integer.MAX_VALUE);
//        mSearchView.onActionViewExpanded();
//        mSearchView.setQueryHint(getString(R.string.search_tips));
//        mSearchView.setOnQueryTextListener(this);
//        mSearchView.setSubmitButtonEnabled(true);
//        try {
//            Field field = mSearchView.getClass().getDeclaredField("mGoButton");
//            field.setAccessible(true);
//            ImageView mGoButton = (ImageView) field.get(mSearchView);
//            mGoButton.setImageResource(R.drawable.actionbar_search);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return super.onCreateOptionsMenu(menu);
//    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();//返回箭头结束界面
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
