package cn.intersteller.darkintersteller.ui;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.intersteller.darkintersteller.R;

/**
 * Created by Day on 2016/12/25.
 */

public class BaseActivity extends AppCompatActivity {


    ImageView mBarDisco;
    ImageView mBarMusic;
    ImageView mBarFriends;
    ImageView mBarSearch;
    LinearLayout mSearchLayout;
    Toolbar mToolbar;
    Toolbar toolbar;
    private MenuItem inboxMenuItem;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    private void setupToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:OnCreate Method has been created, run FindViewById again to generate code
        initView();
    }

    public void initView() {
        mBarDisco = (ImageView) findViewById(R.id.bar_disco);
        mBarMusic = (ImageView) findViewById(R.id.bar_music);
        mBarFriends = (ImageView) findViewById(R.id.bar_friends);
        mBarSearch = (ImageView) findViewById(R.id.bar_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public MenuItem getInboxMenuItem() {
        return inboxMenuItem;
    }

    public ImageView get_bar_disco() {
        return mBarDisco;
    }

    public ImageView get_bar_music() {
        return mBarMusic;
    }

    public ImageView get_bar_friends() {
        return mBarFriends;
    }

    public LinearLayout get_search_layout() {
        return mSearchLayout;
    }


}
