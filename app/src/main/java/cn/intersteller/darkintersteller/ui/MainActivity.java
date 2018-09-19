package cn.intersteller.darkintersteller.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.adapter.OutterFragmentAdapter;
import cn.intersteller.darkintersteller.outterfragment.FirstFragment;
import cn.intersteller.darkintersteller.outterfragment.SecFragment;
import cn.intersteller.darkintersteller.outterfragment.ThirdFragment;
import cn.intersteller.darkintersteller.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private long time = 0;
    ImageView mBarDisco;
    ImageView mBarMusic;
    ImageView mBarFriends;
    ImageView mBarSearch;
    LinearLayout mSearchLayout;
    ViewPager mVpMianActivity;
    Toolbar toolbar;

    FloatingActionButton mFab;
    private FloatingActionButton fab;

    private boolean pendingIntroAnimation;
    private List<Fragment> mFragmntList = new ArrayList<>(3);

    private OutterFragmentAdapter outterFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate", "onCreate");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mBarDisco = (ImageView) findViewById(R.id.bar_disco);
        mBarMusic = (ImageView) findViewById(R.id.bar_music);
        mBarFriends = (ImageView) findViewById(R.id.bar_friends);
        mBarSearch = (ImageView) findViewById(R.id.bar_search);
        mSearchLayout = (LinearLayout) findViewById(R.id.search_layout);
        mVpMianActivity = (ViewPager) findViewById(R.id.vp_mian_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        startIntroAnimation();
        addFragmet();
        initWidgets();
        mVpMianActivity.setCurrentItem(0);
        mBarDisco.setOnClickListener(this);
        mBarMusic.setOnClickListener(this);
        mBarFriends.setOnClickListener(this);
        mBarSearch.setOnClickListener(this);

    }

    private void addFragmet() {
        mFragmntList.add(FirstFragment.newInstance());
        mFragmntList.add(SecFragment.newInstance());
        mFragmntList.add(ThirdFragment.newInstance());

    }

    private void initWidgets() {
        outterFragmentAdapter = new OutterFragmentAdapter(getSupportFragmentManager(), mFragmntList);
        mVpMianActivity.setAdapter(outterFragmentAdapter);
        mVpMianActivity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBarDisco.setSelected(true);
                        mBarMusic.setSelected(false);
                        mBarFriends.setSelected(false);
                        break;
                    case 1:
                        mBarDisco.setSelected(false);
                        mBarMusic.setSelected(true);
                        mBarFriends.setSelected(false);
                        break;
                    case 2:
                        mBarDisco.setSelected(false);
                        mBarMusic.setSelected(false);
                        mBarFriends.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_disco:
                mBarDisco.setSelected(true);
                mBarMusic.setSelected(false);
                mBarFriends.setSelected(false);
                mVpMianActivity.setCurrentItem(0);
                break;
            case R.id.bar_music:
                mBarDisco.setSelected(false);
                mBarMusic.setSelected(true);
                mBarFriends.setSelected(false);
                mVpMianActivity.setCurrentItem(1);
                break;
            case R.id.bar_friends:
                mBarDisco.setSelected(false);
                mBarMusic.setSelected(false);
                mBarFriends.setSelected(true);
                mVpMianActivity.setCurrentItem(2);
                break;
            case R.id.bar_search:
                break;
            case R.id.fab:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
            Log.i("delay", "delay");
        }
        return true;
    }

    private void startIntroAnimation() {
        Log.i("delay", "delay");
        fab.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        int actionBarSzie = ScreenUtils.dpToPx(56);
        toolbar.setTranslationY(-actionBarSzie);
        toolbar.animate().translationY(0).setStartDelay(500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 1000)) {
                Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                finish();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                startActivity(intent);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
