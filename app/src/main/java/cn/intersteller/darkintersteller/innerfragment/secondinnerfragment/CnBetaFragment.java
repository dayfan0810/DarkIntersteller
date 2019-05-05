package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.NewsBean;
import cn.intersteller.darkintersteller.utils.BaseResponseObjectResponse;
import cn.intersteller.darkintersteller.utils.NetKit;
import cn.intersteller.darkintersteller.utils.NewsItem;
import cn.intersteller.darkintersteller.utils.NewsListObject;
import cn.intersteller.darkintersteller.utils.ResponseObject;
import okhttp3.Response;


public class CnBetaFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Banner mBanner;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cnbeta_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.cnbeta_fragment_swipeRefreshLayout);
        mBanner = view.findViewById(R.id.cnbeta_fragment_banner);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.blue
                , R.color.oriange
                , R.color.black
                , R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.cnbeta_fragment_recyclerView);
        onRefresh();
        return view;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        mNewsBeanList.clear();
        requestNews();
    }


    public void requestNews() {
        //2.CNBETA


        Log.i("deng111", "即将执行getNewslistByPage");

        NetKit.getNewslistByPage(this, 1, "all", newsPage);

    }

    BaseResponseObjectResponse newsPage = new BaseResponseObjectResponse<NewsListObject>(
            new TypeToken<ResponseObject<NewsListObject>>() {
            }) {
        private int size = 0;
        private List<NewsItem> itemList;

        @Override
        public ResponseObject<NewsListObject> convertResponse(Response response) throws Throwable {
            Log.i("deng111", "convertResponse,response = " + response);
            Log.i("deng111", "itemList = "+itemList.size());

            int offsetFirst = -1;
            int offsetSecond = -1;
            int offsetThird = -1;
            boolean findFirst = false;
            boolean findSecond = false;
            boolean findThird = false;
            ResponseObject<NewsListObject> responseObject = super.convertResponse(response);
            boolean calNew = responseObject.getResult().getPage() == 1;
            itemList = responseObject.getResult().getList();
            Log.i("deng111", "itemList = "+itemList.size());
            for (NewsItem item : itemList) {
                Log.i("deng111", "item = "+item.getContent());
                if (item.getCounter() != null && item.getComments() != null) {
                    String title = item.getTitle();
                    int num = Integer.parseInt(item.getCounter());
                    if (num > 9999) {
                        item.setCounter("9999+");
                    }
                    num = Integer.parseInt(item.getComments());
                    if (num > 999) {
                        item.setComments("999+");
                    }
                } else {
                    item.setCounter("0");
                    item.setComments("0");
                }
                item.setTitle(item.getTitle().replaceAll("<.*?>", ""));
                StringBuilder sb = new StringBuilder(
                        Html.fromHtml(item.getHometext().replaceAll("<.*?>|[\\r|\\n]", "")));
                if (sb.length() > 140) {
                    item.setSummary(sb.replace(140, sb.length(), "...").toString());
                } else {
                    item.setSummary(sb.toString());
                }
                if (item.getThumb().contains("thumb")) {
                    item.setLargeImage(
                            item.getThumb().replaceAll("(\\.\\w{3,4})?_100x100|thumb/mini/", ""));
                }

            }

            return responseObject;
        }

        /**
         * @param result
         */
        @Override
        protected void onSuccess(NewsListObject result) {
            Log.i("deng111", "onSuccess = ");

        }



        @Override
        public void onFinish() {

        }
    };


}
