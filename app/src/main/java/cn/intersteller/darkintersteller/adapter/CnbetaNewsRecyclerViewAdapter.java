package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.CnbetaNewsBean;
import cn.intersteller.darkintersteller.controller.ImageLoader;
import cn.intersteller.darkintersteller.utils.ImageLoaderUtils;

public class CnbetaNewsRecyclerViewAdapter extends RecyclerView.Adapter<CnbetaNewsRecyclerViewAdapter.ItemViewHolder> {
    Context mContext;
    List<CnbetaNewsBean> mNewsBeans;
    ImageLoader mImageLoader;

    public CnbetaNewsRecyclerViewAdapter(Context context, List<CnbetaNewsBean> beansList, RecyclerView recyclerView, final LinearLayoutManager manager) {
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

    public CnbetaNewsBean getItem(int position) {
        return mNewsBeans == null ? null : mNewsBeans.get(position);
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
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        CnbetaNewsBean newsBean = mNewsBeans.get(position);
        if (newsBean == null) {
            return;
        }
        holder.top_news_item_title.setText(newsBean.getTitle());
        holder.top_news_item_date.setText(newsBean.getInputtime());
//        holder.top_news_item_img.setTag(newsBean.newsIconUrl);
        if (mOnItemClickListener != null) {
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).top_news_item_img, newsBean.getUrl_show());
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

        private final TextView top_news_item_title;
        private final ImageView top_news_item_img;
        private final TextView top_news_item_date;

        public ItemViewHolder(View itemView) {
            super(itemView);
            top_news_item_img = itemView.findViewById(R.id.top_news_item_icon);
            top_news_item_title = itemView.findViewById(R.id.top_news_item_title);
            top_news_item_date = itemView.findViewById(R.id.top_news_item_date);
        }
    }
}
