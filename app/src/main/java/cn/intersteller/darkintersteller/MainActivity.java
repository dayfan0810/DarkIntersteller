package cn.intersteller.darkintersteller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cn.intersteller.darkintersteller.adapter.OutterFragmentAdapter;
import cn.intersteller.darkintersteller.test.ImmersionTestActivity;
import cn.intersteller.darkintersteller.ui.NetSearchActivity;
import cn.intersteller.darkintersteller.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private long time = 0;
    private ImageView mBarDisco;
    private ImageView mBarMusic;
    private ImageView mBarFriends;
    private ImageView mBarSearch;
    LinearLayout mSearchLayout;
    private ViewPager mVpMianActivity;
    private Toolbar toolbar;

    private FloatingActionButton fab;

    private boolean pendingIntroAnimation;

    private OutterFragmentAdapter outterFragmentAdapter;
//    private UshkNewsGrabber mUshkNewsGrabber = new UshkNewsGrabber();
//    private CnbetaNewsGrabber mCnbetaNewsGrabber = new CnbetaNewsGrabber();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate", "onCreate");
        fab = findViewById(R.id.fab);
        mBarDisco = findViewById(R.id.bar_disco);
        mBarMusic = findViewById(R.id.bar_music);
        mBarFriends = findViewById(R.id.bar_friends);
        mBarSearch = findViewById(R.id.bar_search);
//        mSearchLayout = findViewById(R.id.search_layout);
        mVpMianActivity = findViewById(R.id.vp_mian_activity);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
//        addFragmet();
        initWidgets();
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ImmersionTestActivity.class);
            startActivity(intent);
//                SnackbarsetOffscreenPageLimit.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
        });
        mBarDisco.setOnClickListener(this);
        mBarMusic.setOnClickListener(this);
        mBarFriends.setOnClickListener(this);
        mBarSearch.setOnClickListener(this);
        //1.USHKNEWS
        //        ArrayList ushkNewsBeanByCallable = mUshkNewsGrabber.getUSHKNewsBeanDerectely();
        //        ArrayList ushkNewsBeanByCallable = mUshkNewsGrabber.getUSHKNewsBeanByCallable();
        //        Log.i("deng4","ushkNewsBeanByCallable = "+ushkNewsBeanByCallable.size());

        setCurrentItem(0);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void setCurrentItem(int position) {
        boolean isOne = false;
        boolean isTwo = false;
        boolean isThree = false;
        switch (position) {
            case 0:
                isOne = true;
                break;
            case 1:
                isTwo = true;
                break;
            case 2:
                isThree = true;
                break;
            default:
                isTwo = true;
                break;
        }
        mVpMianActivity.setCurrentItem(position);
        mBarDisco.setSelected(isOne);
        mBarMusic.setSelected(isTwo);
        mBarFriends.setSelected(isThree);
    }

    private void initWidgets() {
        outterFragmentAdapter = new OutterFragmentAdapter(getSupportFragmentManager(), MyApplication.mFragmntList);
        mVpMianActivity.setAdapter(outterFragmentAdapter);
        mVpMianActivity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //oncreate时不会走这个方法, 导致三个顶部图标都是灰色,没有被选取
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
                if (mVpMianActivity.getCurrentItem() != 0) {
                    mVpMianActivity.setCurrentItem(0);
                    mBarDisco.setSelected(true);
                    mBarMusic.setSelected(false);
                    mBarFriends.setSelected(false);
                }
                break;
            case R.id.bar_music:
                if (mVpMianActivity.getCurrentItem() != 1) {
                    mVpMianActivity.setCurrentItem(1);
                    mBarMusic.setSelected(true);
                    mBarDisco.setSelected(false);
                    mBarFriends.setSelected(false);
                }
                break;
            case R.id.bar_friends:
                if (mVpMianActivity.getCurrentItem() != 2) {
                    mVpMianActivity.setCurrentItem(2);
                    mBarFriends.setSelected(true);
                    mBarDisco.setSelected(false);
                    mBarMusic.setSelected(false);
                }
                break;
            case R.id.bar_search:
                final Intent intent = new Intent(MainActivity.this, NetSearchActivity.class);
                MainActivity.this.startActivity(intent);
//                try {
//                    DealThread t1 = new DealThread(this);
//                    t1.setFlag("a");
//
//                    Thread thread1 = new Thread(t1);
//                    thread1.start();
//
//                    Thread.sleep(100);
//
//                    t1.setFlag("b");
//                    Thread thread2 = new Thread(t1);
//                    thread2.start();
//
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }

                //测试volatile可见性
//                try {
//                    VolatileVisibleTest volatileVisibleTest = new VolatileVisibleTest();
//                    volatileVisibleTest.testVolatileVisibleTest();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

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
//                super.finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("deng", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("deng", "onStop");
    }


}
