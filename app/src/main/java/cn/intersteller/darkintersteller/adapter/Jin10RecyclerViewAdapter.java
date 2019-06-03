package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.WebGrabber.USHK.USHKNewsBean;

public class Jin10RecyclerViewAdapter extends RecyclerView.Adapter<Jin10RecyclerViewAdapter.ItemViewHolder> {
    Context mContext;
    List<USHKNewsBean> mNewsBeans;
//    ImageLoader mImageLoader;

    public Jin10RecyclerViewAdapter(Context context, List<USHKNewsBean> beansList, RecyclerView recyclerView, final LinearLayoutManager manager) {
        this.mNewsBeans = beansList;
        this.mContext = context;
//        notifyDataSetChanged();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    //停止滚动时加载
//                    mImageLoader.load(manager.findFirstVisibleItemPosition(),
//                            manager.findLastVisibleItemPosition());

                } else {

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    //供外部fragment去设置回调,回调在onBindViewHolder中触发
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public USHKNewsBean getItem(int position) {
        return mNewsBeans == null ? null : mNewsBeans.get(position);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cmy_timeline_market_live_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mNewsBeans.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        USHKNewsBean newsBean = mNewsBeans.get(position);
        if (newsBean == null) {
            return;
        }
        holder.market_live_text.setText(newsBean.getText());
        holder.market_live_time.setText(newsBean.getTime());
//        holder.top_news_item_img.setTag(newsBean.newsIconUrl);
        if (mOnItemClickListener != null) {
//            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).top_news_item_img, newsBean.getNewsIconUrl());
            ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(((ItemViewHolder) holder).itemView, position);
                }
            });

            ((ItemViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(((ItemViewHolder) holder).itemView, position);
                    return false;
                }
            });
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView market_live_text;
        private final TextView market_live_time;

        public ItemViewHolder(View itemView) {
            super(itemView);
            market_live_text = itemView.findViewById(R.id.market_live_text);
            market_live_time = itemView.findViewById(R.id.market_live_time);
        }
    }
}
