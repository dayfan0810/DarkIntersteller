package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.cnbeta_news_item_layout, parent, false);
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
        holder.cnbeta_news_item_title.setText(newsBean.getTitle());
        holder.cnbeta_news_item_date.setText(newsBean.getInputtime());
        //下面的预览要用正则
        StringBuilder sb = new StringBuilder(
                Html.fromHtml(newsBean.getHometext().replaceAll("<.*?>|[\\r|\\n]", "")));
        holder.cnbeta_news_item_summary.setText(sb);
        //浏览次数要用占位符
        String format1 = String.format(mContext.getResources().getString(R.string.view_nums), newsBean.getMview());
        holder.cnbeta_news_item_view_counts.setText(format1);
        //评论次数要用户占位符
        String format2 = String.format(mContext.getResources().getString(R.string.conments_nums), newsBean.getComments());

        holder.cnbeta_news_item_conment_counts.setText(format2);
//        holder.cnbeta_news_item_icon.setTag(newsBean.newsIconUrl);
        if (mOnItemClickListener != null) {
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).cnbeta_news_item_icon, newsBean.thumb);
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

        private final TextView cnbeta_news_item_title;
        private final ImageView cnbeta_news_item_icon;
        private final TextView cnbeta_news_item_date;
        private final TextView cnbeta_news_item_conment_counts;
        private final TextView cnbeta_news_item_summary;
        private final TextView cnbeta_news_item_view_counts;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cnbeta_news_item_icon = itemView.findViewById(R.id.cnbeta_news_item_icon);
            cnbeta_news_item_title = itemView.findViewById(R.id.cnbeta_news_item_title);
            cnbeta_news_item_date = itemView.findViewById(R.id.cnbeta_news_item_date);
            cnbeta_news_item_conment_counts = itemView.findViewById(R.id.cnbeta_news_item_conment_counts);
            cnbeta_news_item_view_counts = itemView.findViewById(R.id.cnbeta_news_item_view_counts);
            cnbeta_news_item_summary = itemView.findViewById(R.id.cnbeta_news_item_summary);
        }
    }

    private List<CnbetaNewsBean> data = new ArrayList<>();
    public List<CnbetaNewsBean> getData() {
        return data;
    }

    public void setData(List<CnbetaNewsBean> data) {
        this.data = data;
    }
}
