package cn.intersteller.darkintersteller.bean;

import java.io.Serializable;

public class CnbetaNewsBean implements Serializable {
    public String title;
    public String hometext;
    public int mview;
    public String inputtime;
    public String thumb;
    public String url_show;
    public int comments;

    public int getMview() {
        return mview;
    }

    public void setMview(int mview) {
        this.mview = mview;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHometext() {
        return hometext;
    }

    public void setHometext(String hometext) {
        this.hometext = hometext;
    }


    public String getInputtime() {
        return inputtime;
    }

    public void setInputtime(String inputtime) {
        this.inputtime = inputtime;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl_show() {
        return url_show;
    }

    public void setUrl_show(String url_show) {
        this.url_show = url_show;
    }


}
