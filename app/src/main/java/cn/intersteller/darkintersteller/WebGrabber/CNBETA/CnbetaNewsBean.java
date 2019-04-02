package cn.intersteller.darkintersteller.WebGrabber.CNBETA;

import java.io.Serializable;

public class CnbetaNewsBean implements Serializable {
    public String newsIconUrl;
    public String newsTitle;
    public String newsDate;
    public String news_preview;
    public String url;

    public CnbetaNewsBean(String newsIconUrl, String newsTitle, String newsDate, String news_preview, String url) {
        this.newsTitle = newsTitle;
        this.news_preview = news_preview;
        this.newsDate = newsDate;
        this.newsIconUrl = newsIconUrl;
        this.url = url;
    }

    public String getNewsIconUrl() {
        return newsIconUrl;
    }

    public void setNewsIconUrl(String newsIconUrl) {
        this.newsIconUrl = newsIconUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNews_preview() {
        return news_preview;
    }

    public void setNews_preview(String news_preview) {
        this.news_preview = news_preview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
