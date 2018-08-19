package cn.intersteller.darkintersteller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;

import cn.intersteller.darkintersteller.bean.NewsBean;
import cn.intersteller.darkintersteller.utils.ImageLoaderUtils;

public class NewsDetailActivity extends Activity {

    private NewsBean mNews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mNews = (NewsBean) getIntent().getSerializableExtra("newsItem");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.getNewsTitle());

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getNewsIconUrl());

    }
}
