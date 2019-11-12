package cn.intersteller.darkintersteller.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import cn.intersteller.darkintersteller.bean.NewsBean;

public class ImageLoader {
    private Context mContext;
    private LruCache<String, Bitmap> mLrucache;
    private String[] imgPath;
    private List<NewsBean> mBeansList;
    private RecyclerView mRecycleView;
    private Set<NewsAynsTaskImgView> tasks = new HashSet();

    public ImageLoader(Context context, RecyclerView recyclerView, String[] imgPaths) {
        mContext = context;
        mLrucache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        for (int i = 0; i < mBeansList.size(); i++) {
            String newsIconUrl = mBeansList.get(i).getNewsIconUrl();
            imgPaths[i] = newsIconUrl;
        }


    }

    public void load(int startPos, int endPos) {
        for (int index = startPos; index <= endPos; index++) {
            NewsAynsTaskImgView task = new NewsAynsTaskImgView(imgPath[index]);
            tasks.add(task);
            task.execute(new String[0]);
        }
    }

    public void cancelAllLoadingTask() {
        for (NewsAynsTaskImgView task : this.tasks) {
            task.cancel(true);
        }
    }


    class NewsAynsTaskImgView extends AsyncTask<String, Void, Bitmap> {
        String url;

        public NewsAynsTaskImgView(String url) {
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imgUrl = strings[0];
            Bitmap bitmapFromURL = getBitmapFromURL(imgUrl);
            if (bitmapFromURL == null) {
                Log.i("deng", "bitmap is null from net");
                return null;
            }
            addBitmapToMemCache(imgUrl, bitmapFromURL);
            return bitmapFromURL;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imgViewWithTag = mRecycleView.findViewWithTag(this.url);
            if (imgViewWithTag.getTag().equals(url) && bitmap != null) {
                imgViewWithTag.setImageBitmap(bitmap);
            }

        }
    }

    private Bitmap getBitmapFromURL(String imgUrl) {
        try {
            Bitmap bitmap = Glide.with(mContext).load(imgUrl).asBitmap().into(64, 64).get();
            Log.i("deng", "bitmap ==null ? " + (bitmap == null));
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addBitmapToMemCache(String imgUrl, Bitmap bitmap) {
        Bitmap bitmapFromMemCache = getBitmapFromMemCache(imgUrl);
        if (bitmapFromMemCache == null) {
            mLrucache.put(imgUrl, bitmap);
        }

    }

    private Bitmap getBitmapFromMemCache(String imgUrl) {
        return null;
    }
}
