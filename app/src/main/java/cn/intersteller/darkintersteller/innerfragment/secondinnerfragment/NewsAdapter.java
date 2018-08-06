package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.NewsBean;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemViewHolder> {
    Context mContext;
    List<NewsBean> mNewsBeans;

    public NewsAdapter(Context context, List<NewsBean> list) {
        Log.i("deng","NewsAdapter ");
        this.mNewsBeans = list;
        this.mContext = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_news_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mNewsBeans.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.i("deng","onBindViewHolder ");
        NewsBean newsBean = mNewsBeans.get(position);
        if (newsBean == null) {
            return;
        }
        Log.i("deng","newsBean.getNewsTitle() = "+newsBean.getNewsTitle());
        holder.top_news_item_title.setText(newsBean.getNewsTitle());

//        holder.top_news_item_img.setImageBitmap(newsBean.);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView top_news_item_img;
        private final TextView top_news_item_title;
        private final TextView top_news_item_content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            top_news_item_img = itemView.findViewById(R.id.top_news_item_icon);
            top_news_item_title = itemView.findViewById(R.id.top_news_item_title);
            top_news_item_content = itemView.findViewById(R.id.top_news_item_content);

        }
    }
}
