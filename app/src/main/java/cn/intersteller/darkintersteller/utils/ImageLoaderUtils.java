package cn.intersteller.darkintersteller.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.intersteller.darkintersteller.R;

public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).crossFade().into(imageView);
    }


}
