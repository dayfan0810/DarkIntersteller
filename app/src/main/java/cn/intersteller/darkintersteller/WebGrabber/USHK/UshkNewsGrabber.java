package cn.intersteller.darkintersteller.WebGrabber.USHK;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UshkNewsGrabber {
    private ArrayList<USHKNewsBean> mUSHKNewsBeans = new ArrayList<>();
    private final String mHomePage_jin10 = "https://www.jin10.com/";

    public ArrayList getUSHKNewsBeanByCallable() {
        ExecutorService exs = Executors.newCachedThreadPool();
        Future<ArrayList> submit = exs.submit(new UshkNewsGrabberCallable());
        ArrayList arrayList = null;
        try {
            arrayList = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    class UshkNewsGrabberCallable implements Callable<ArrayList> {

        @Override
        public ArrayList call() throws Exception {
            Document doc = Jsoup.connect(mHomePage_jin10).get();
            Elements timeLinks = doc.select("div.jin-flash_h");    //解析来获取每条新闻的时间
            Elements contentLinks = doc.select("div.jin-flash_b");   //解析来获取每条新闻的时间与来源
            Log.i("deng4", "contentLinks.size() = " + contentLinks.size());
            for (int i = 0; i < contentLinks.size(); i++) {
                String flash_h_text = contentLinks.get(i).select("h4").text();
//                        Elements flash_icon = timeLinks.select("div.jin-flash_icon");
                String flash_time_text = timeLinks.get(i).select("div.jin-flash_time").text();
                if (flash_h_text == null || flash_h_text.equals("") || flash_time_text == null || flash_time_text.equals("")) {
                    continue;
                }
//                Log.w("deng4", "h4 = " + flash_h_text.substring(0, 20) +
//                        ", flash_time_text = " + flash_time_text);
                USHKNewsBean ushkNewsBean = new USHKNewsBean(flash_time_text, flash_h_text);
                mUSHKNewsBeans.add(ushkNewsBean);
            }
            return mUSHKNewsBeans;
        }
    }


    public ArrayList getUSHKNewsBeanDerectely() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mHomePage_jin10).get();
                    Elements timeLinks = doc.select("div.jin-flash_h");    //解析来获取每条新闻的时间
                    Elements contentLinks = doc.select("div.jin-flash_b");   //解析来获取每条新闻的时间与来源
                    Log.i("deng4", "contentLinks.size() = " + contentLinks.size());
                    for (int i = 0; i < contentLinks.size(); i++) {
                        String flash_h_text = contentLinks.get(i).select("h4").text();
//                        Elements flash_icon = timeLinks.select("div.jin-flash_icon");
                        String flash_time_text = timeLinks.get(i).select("div.jin-flash_time").text();
                        if (flash_h_text == null || flash_h_text.equals("") || flash_time_text == null || flash_time_text.equals("")) {
                            continue;
                        }
//                        Log.w("deng4", "h4 = " + flash_h_text.substring(0, 20) +
//                                ", flash_time_text = " + flash_time_text);
                        USHKNewsBean ushkNewsBean = new USHKNewsBean(flash_time_text, flash_h_text);
                        mUSHKNewsBeans.add(ushkNewsBean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return mUSHKNewsBeans;
    }

}
