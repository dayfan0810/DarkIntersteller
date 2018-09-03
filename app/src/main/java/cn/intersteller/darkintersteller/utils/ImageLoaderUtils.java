package cn.intersteller.darkintersteller.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.intersteller.darkintersteller.R;

public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            Toast.makeText(context, "ImageLoaderUtils image == nulll", Toast.LENGTH_SHORT).show();
            return;
        }
//        Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher).crossFade().into(imageView);
        Glide.with(context)
                .load(url)
//                .placeholder(R.drawable.default_cover)
//                .skipMemoryCache(true)
                .diskCacheStrategy( DiskCacheStrategy.ALL )
                .into(imageView);
    }


}
