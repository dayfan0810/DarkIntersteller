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
import cn.intersteller.darkintersteller.WebGrabber.CNBETA.CnbetaNewsGrabber;
import cn.intersteller.darkintersteller.WebGrabber.USHK.UshkNewsGrabber;
import cn.intersteller.darkintersteller.adapter.OutterFragmentAdapter;
import cn.intersteller.darkintersteller.outterfragment.FirstFragment;
import cn.intersteller.darkintersteller.outterfragment.SecFragment;
import cn.intersteller.darkintersteller.outterfragment.ThirdFragment;
import cn.intersteller.darkintersteller.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private UshkNewsGrabber mUshkNewsGrabber = new UshkNewsGrabber();
    private CnbetaNewsGrabber mCnbetaNewsGrabber = new CnbetaNewsGrabber();

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
        mSearchLayout = findViewById(R.id.search_layout);
        mVpMianActivity = findViewById(R.id.vp_mian_activity);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        addFragmet();
        initWidgets();
        mVpMianActivity.setCurrentItem(1);
        mBarDisco.setOnClickListener(this);
        mBarMusic.setOnClickListener(this);
        mBarFriends.setOnClickListener(this);
        mBarSearch.setOnClickListener(this);
        //1.USHKNEWS
        //        ArrayList ushkNewsBeanByCallable = mUshkNewsGrabber.getUSHKNewsBeanDerectely();
        //        ArrayList ushkNewsBeanByCallable = mUshkNewsGrabber.getUSHKNewsBeanByCallable();
        //        Log.i("deng4","ushkNewsBeanByCallable = "+ushkNewsBeanByCallable.size());

        //2.CNBETA

//         BaseResponseObjectResponse newsPage = new BaseResponseObjectResponse<NewsListObject>(
//                new TypeToken<ResponseObject<NewsListObject>>() {
//                }) {
//            private int size = 0;
//            private List<NewsItem> itemList;
//
//            @Override
//            public ResponseObject<NewsListObject> convertResponse(Response response) throws Throwable {
//                Log.i("deng111", "convertResponse,response = " + response);
//                Log.i("deng111", "itemList = "+itemList.size());
//
//                int offsetFirst = -1;
//                int offsetSecond = -1;
//                int offsetThird = -1;
//                boolean findFirst = false;
//                boolean findSecond = false;
//                boolean findThird = false;
//                ResponseObject<NewsListObject> responseObject = super.convertResponse(response);
//                boolean calNew = responseObject.getResult().getPage() == 1;
//                itemList = responseObject.getResult().getList();
//                Log.i("deng111", "itemList = "+itemList.size());
//                for (NewsItem item : itemList) {
//                    Log.i("deng111", "item = "+item.getContent());
//                    if (item.getCounter() != null && item.getComments() != null) {
//                        String title = item.getTitle();
//                        int num = Integer.parseInt(item.getCounter());
//                        if (num > 9999) {
//                            item.setCounter("9999+");
//                        }
//                        num = Integer.parseInt(item.getComments());
//                        if (num > 999) {
//                            item.setComments("999+");
//                        }
//                    } else {
//                        item.setCounter("0");
//                        item.setComments("0");
//                    }
//                    item.setTitle(item.getTitle().replaceAll("<.*?>", ""));
//                    StringBuilder sb = new StringBuilder(
//                            Html.fromHtml(item.getHometext().replaceAll("<.*?>|[\\r|\\n]", "")));
//                    if (sb.length() > 140) {
//                        item.setSummary(sb.replace(140, sb.length(), "...").toString());
//                    } else {
//                        item.setSummary(sb.toString());
//                    }
//                    if (item.getThumb().contains("thumb")) {
//                        item.setLargeImage(
//                                item.getThumb().replaceAll("(\\.\\w{3,4})?_100x100|thumb/mini/", ""));
//                    }
//
//                }
//
//                return responseObject;
//            }
//
//            /**
//             * @param result
//             */
//            @Override
//            protected void onSuccess(NewsListObject result) {
//
//            }
//
//
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        NetKit.getNewslistByPage(this, 1, "all", newsPage);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startIntroAnimation();
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
