package cn.intersteller.darkintersteller.WebGrabber.CNBETA;

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

public class CnbetaNewsGrabber {
    private ArrayList<CnbetaNewsBean> mUSHKNewsBeans = new ArrayList<>();
    private final String mHomePage_cnBeta = "https://www.cnbeta.com/";

    class CnbetaNewsGrabberCallable implements Callable<ArrayList> {

        @Override
        public ArrayList call() throws Exception {
            Document doc = Jsoup.connect(mHomePage_cnBeta).get();
            //解析来获取轮播图页面
            Elements scroll_content = doc.select("div.cnbeta-headlines");
            //解析来获取下面的主页新闻所有内容
            Elements main_content = doc.select("div.cnbeta-update");
            Elements items_area = main_content.select("div.items-area");
            //获取每个ITEM
            Elements item = items_area.select("div.item");
//            Log.w("deng4", "h4 = " + item.toString());
            for (int i = 0; i < item.size(); i++) {
                String title = item.get(i).select("dt").text();
                if (title == null || title.equals("")){
                    continue;
                }
                Log.w("deng4", "title = " + title.substring(0, 10));

            }
//            for (int i = 0; i < contentLinks.size(); i++) {
//                String flash_h_text = contentLinks.get(i).select("h4").text();
////                        Elements flash_icon = timeLinks.select("div.jin-flash_icon");
//                String flash_time_text = timeLinks.get(i).select("div.jin-flash_time").text();
//                if (flash_h_text == null || flash_h_text.equals("") || flash_time_text == null || flash_time_text.equals("")) {
//                    continue;
//                }
////                Log.w("deng4", "h4 = " + flash_h_text.substring(0, 20) +
////                        ", flash_time_text = " + flash_time_text);
//                new CnbetaNewsBean();
//            }
            return mUSHKNewsBeans;
        }
    }

    public ArrayList getCnbetaNewsBeanByCallable() {
        ExecutorService exs = Executors.newCachedThreadPool();
        Future<ArrayList> submit = exs.submit(new CnbetaNewsGrabber.CnbetaNewsGrabberCallable());
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
}
