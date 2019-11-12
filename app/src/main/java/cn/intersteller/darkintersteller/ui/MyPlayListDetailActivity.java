package cn.intersteller.darkintersteller.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.adapter.PlayListDetailActivityRecyclerViewAdapter;
import cn.intersteller.darkintersteller.utils.StatusBarUtils;

public class MyPlayListDetailActivity extends AppCompatActivity {
    private static final String TAG = "MyPlayListDetailActivity";
    private Toolbar mToolbar;
    private ImageView mHeaderBg;
    //    private RelativeLayout fl_head_content;
    private RecyclerView mRecyclerView;
    private PlayListDetailActivityRecyclerViewAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_details);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        mToolbar.inflateMenu(R.menu.toolbar_right_menu);
//        mToolbar.setTitle(R.string.title);
        mToolbar.setTitleTextColor(Color.WHITE);

        mHeaderBg = (ImageView) findViewById(R.id.header_image);
//        fl_head_content = (RelativeLayout) findViewById(R.id.fl_head_content);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMyAdapter = new PlayListDetailActivityRecyclerViewAdapter();
        mMyAdapter.setData(getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMyAdapter);
        // toolbar 的高
        int toolbarHeight = mToolbar.getLayoutParams().height;
        Log.i(TAG, "toolbar height:" + toolbarHeight);
        final int headerBgHeight = toolbarHeight + getStatusBarHeight(this);
        Log.i(TAG, "headerBgHeight:" + headerBgHeight);
        ViewGroup.LayoutParams params = mHeaderBg.getLayoutParams();
//        ViewGroup.LayoutParams params = fl_head_content.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = headerBgHeight;

        mHeaderBg.setImageAlpha(0);
//        fl_head_content.setAlpha(0);

        StatusBarUtils.setTranslucentImageHeader(this, 0, mToolbar);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View headerView = null;

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();
                if (firstVisibleItem == 0) {
                    headerView = recyclerView.getChildAt(firstVisibleItem);
                }
                float alpha = 0;
                if (headerView == null) {
                    alpha = 1;// 如果headerView 为null ,说明已经到达header滑动的最大高度了
                } else {
                    alpha = Math.abs(headerView.getTop()) * 1.0f / headerView.getHeight();
                    Log.i(TAG, "alpha:" + alpha + "top :" + headerView.getTop() + " height: " + headerView.getHeight());
                }

                Drawable drawable = mHeaderBg.getDrawable();
                if (drawable != null) {
                    drawable.mutate().setAlpha((int) (alpha * 255));
                    mHeaderBg.setImageDrawable(drawable);
                }

//                Drawable background = fl_head_content.getBackground();
//                if (background != null) {
//                    background.mutate().setAlpha((int) (alpha * 255));
//                    fl_head_content.setBackground(background);
//                }

            }
        });
    }


    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("item :" + i);
        }
        return list;
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
