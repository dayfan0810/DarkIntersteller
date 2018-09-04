package cn.intersteller.darkintersteller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.NewsBean;
import cn.intersteller.darkintersteller.utils.ImageLoaderUtils;

public class NewsDetailActivity extends Activity {

    private NewsBean mNews;
    private WebView wvContent;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mNews = (NewsBean) getIntent().getSerializableExtra("newsItem");
        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getNewsIconUrl());
        String newsUrl = mNews.getUrl();
        wvContent = findViewById(R.id.wv_content);
        progressbar = findViewById(R.id.progressbar);
        initWebViewSettings();

        wvContent.removeJavascriptInterface("searchBoxJavaBridge_");
        wvContent.removeJavascriptInterface("accessibilityTraversal");
        wvContent.removeJavascriptInterface("accessibility");
        wvContent.loadUrl(newsUrl);
    }

    private void initWebViewSettings() {
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setSavePassword(false);
        wvContent.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });

    }
}
