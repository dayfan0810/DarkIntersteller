package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.RecomendBnnerBean;
import cn.intersteller.darkintersteller.utils.Constant;
import cn.intersteller.darkintersteller.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Day on 2017/1/2.
 */

public class RecommendFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Banner mRecommend_music_banner;
    private List<RecomendBnnerBean.BannersBean> mRecomendBnnerBeans = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recomment_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.recommend_music_swipeRefreshLayout);
        mRecommend_music_banner = view.findViewById(R.id.recommend_music_banner);
//        mRecommend_music_banner.setOnTouchListener(new View.OnTouchListener() {
//
//            private float startX;
//            private float startY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // 记录手指按下的位置
//                        startY = event.getY();
//                        startX = event.getX();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        // 获取当前手指位置
//                        float endY = event.getY();
//                        float endX = event.getX();
//                        float distanceX = Math.abs(endX - startX);
//                        float distanceY = Math.abs(endY - startY);
//                        // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
//                        if (distanceX > distanceY) {
//                            mSwipeRefreshLayout.setEnabled(false);
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        mSwipeRefreshLayout.setEnabled(true);
//                        break;
//                }
//                return false;
//            }
//        });

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        addView();
        return view;
    }

    private void addView() {
//        initBanner();
    }

    private void initBanner() {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
//        mRecomendBnnerBeans.clear();
        requestRecomendBnner();
        initBanner();
//        requestRecomend();
    }

    private void requestRecomendBnner() {
        //一次性获取前100名
        HttpUtil.getHttpUtilInstance().sendOkHttpRequest(Constant.NETEASE_BANNER, new Callback() {

            ArrayList<String> mTypeTitles;
            ArrayList<String> imgs;

            @Override
            public void onFailure(Call call, IOException e) {
                if (getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getContext(), "获取Banner信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    String resultCode = (String) jsonObject.optString("code");
                    if (!resultCode.equals("200")) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Banner获取错误,检查URL", Toast.LENGTH_SHORT).show();
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                        return;
                    }
                    final JSONArray bannersData = jsonObject.getJSONArray("banners");
                    mTypeTitles = new ArrayList<>();
                    imgs = new ArrayList<>();
                    for (int i = 0; i < bannersData.length(); i++) {
                        JSONObject dataItem = (JSONObject) bannersData.get(i);
                        String cover = dataItem.optString("imageUrl");
                        String typeTitle = dataItem.optString("typeTitle");
                        imgs.add(cover);
                        mTypeTitles.add(typeTitle);
                        Log.i("gang1", " imgs = " + imgs.size());
//                        RecomendBnnerBean.BannersBean bannersBean = new RecomendBnnerBean.BannersBean();
//                        bannersBean.setImageUrl(cover);
//                        bannersBean.setTypeTitle(typeTitle);
//                        mRecomendBnnerBeans.add(bannersBean);
                    }
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mRecommend_music_banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//设置页码与标题
                            mRecommend_music_banner.setDelayTime(3000);//设置轮播时间
                            mRecommend_music_banner.setBannerTitles(mTypeTitles);//设置标题
                            Log.i("gang2", " imgs = " + imgs.size());
                            mRecommend_music_banner.setImages(imgs);
                            mRecommend_music_banner.setImageLoader(new MyLoader());
                            mRecommend_music_banner.start();
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
}
