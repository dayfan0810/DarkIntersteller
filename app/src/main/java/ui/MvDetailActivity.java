package ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.HotMVBean;
import cn.intersteller.darkintersteller.utils.Constant;


public class MvDetailActivity extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv_detail);
        ImageView video_preview = (ImageView) findViewById(R.id.video_preview);
        HotMVBean.DataBean item = (HotMVBean.DataBean) getIntent().getSerializableExtra("mvitem");
        int id = item.getId();
        String url_mv = Constant.NETEASEBASE+Constant.NETEASE_MV_ID+id;
        Glide.with(this)
                .load(url_mv)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_cover)
                .dontAnimate()
                .into(video_preview);
    }
}
