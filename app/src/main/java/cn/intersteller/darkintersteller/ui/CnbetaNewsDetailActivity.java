package cn.intersteller.darkintersteller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Locale;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.WebGrabber.CNBETA.CnbetaNewsGrabber;
import cn.intersteller.darkintersteller.bean.CnbetaNewsBean;
import cn.intersteller.darkintersteller.utils.ImageLoaderUtils;

public class CnbetaNewsDetailActivity extends Activity {

    private CnbetaNewsBean mNews;
    private WebView wvContent;
    private ProgressBar progressbar;
    private String webTemplate = "<!DOCTYPE html><html><head><base href=\"http://www.cnbeta.com/\" /><title></title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>"
            + "<link  rel=\"stylesheet\" href=\"file:///android_asset/style.css\" type=\"text/css\"/><style>.title{color: #%s;}</style>"
            + "<script>var config = {\"enableImage\":%s,\"enableFlashToHtml5\":%s,staticLoading:%s};</script>"
            + "<script src=\"file:///android_asset/js/BaseTool.js\"></script>"
            + "<script src=\"file:///android_asset/js/ImageTool.js\"></script>"
            + "<script src=\"file:///android_asset/js/VideoTool.js\"></script></head>"
            + "<body class='%s'><div><div class=\"title\">%s</div><div class=\"from\">%s<span style=\"float: right;margin-top: 3pt;\">%s</span></div><div id=\"introduce\">%s<div class=\"clear\"></div></div><div id=\"content\">%s</div><div class=\"clear foot\">-- The End --</div></div>"
            + "<script src=\"file:///android_asset/js/loder.js\"></script></body></html>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnbeta_news_detail);
        mNews = (CnbetaNewsBean) getIntent().getSerializableExtra("newsItem");
//        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getThumb());
        String newsUrl = mNews.getUrl_show();
        Log.i("deng", "newsUrl =  " + newsUrl);
        wvContent = findViewById(R.id.wv_content);
        progressbar = findViewById(R.id.progressbar);
        initWebViewSettings();

//        wvContent.removeJavascriptInterface("searchBoxJavaBridge_");
//        wvContent.removeJavascriptInterface("accessibilityTraversal");
//        wvContent.removeJavascriptInterface("accessibility");

        String colorString = Integer.toHexString(-15436882);
        Log.i("dengtitleColor", "colorString.substring(2, colorString.length() = " + colorString.substring(2, colorString.length()));
        //
        Toast.makeText(this, "使用jsoup来填充此处正文内容，就不开发了", Toast.LENGTH_LONG).show();
        String data = String.format(Locale.CHINA, webTemplate, colorString.substring(2, colorString.length()),
                true, true, true, "light", mNews.getTitle(), "",
                mNews.getInputtime(), mNews.getHometext(), "使用jsoup来填充此处正文内容，时间不够就不开发了");
        wvContent.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
//        wvContent.loadUrl(newsUrl);
    }

    private void initWebViewSettings() {
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setSavePassword(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
//        wvContent.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                setTitle(title);
//            }
//        });
//        wvContent.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (url != null) view.loadUrl(url);
//                return true;
//            }
//        });

    }
}
