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
import cn.intersteller.darkintersteller.bean.HotMVBean;
import cn.intersteller.darkintersteller.utils.ImageLoaderUtils;

public class HotMVRecyclerViewAdapter extends RecyclerView.Adapter<HotMVRecyclerViewAdapter.ItemViewHolder> {
    Context mContext;
    List<HotMVBean.DataBean> mMVBeans;

    public HotMVRecyclerViewAdapter(Context context, List<HotMVBean.DataBean> mVBeans, RecyclerView recyclerView, final LinearLayoutManager manager) {
        this.mMVBeans = mVBeans;
        this.mContext = context;
//        notifyDataSetChanged();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
////                    //停止滚动时加载
////                    mImageLoader.load(manager.findFirstVisibleItemPosition(),
////                            manager.findLastVisibleItemPosition());
//
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
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

    public HotMVBean.DataBean getItem(int position) {
        return mMVBeans == null ? null : mMVBeans.get(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mv_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mMVBeans.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        HotMVBean.DataBean mvBean = mMVBeans.get(position);
        if (mvBean == null) {
            return;
        }
        holder.item_mv_list_tv_playCount.setText(mvBean.getPlayCount()+"次");
        holder.item_mv_list_tv_title.setText(mvBean.getName());
        holder.item_mv_list_tv_num.setText(String.valueOf(position + 1));
        setSortNumTextSize(holder, position);
        if (mOnItemClickListener != null) {
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).item_mv_list_iv_cover, mvBean.getCover());
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

    private void setSortNumTextSize(ItemViewHolder itemViewHolder, int position) {
        if (position == 0) {
            itemViewHolder.item_mv_list_tv_num.setTextSize(26);
            itemViewHolder.item_mv_list_tv_num.setTextColor(
                    mContext.getResources().getColor(R.color.color_top_rank));
        } else if (position == 1) {
            itemViewHolder.item_mv_list_tv_num.setTextSize(24);
            itemViewHolder.item_mv_list_tv_num.setTextColor(
                    mContext.getResources().getColor(R.color.color_top_rank));
        } else if (position == 2) {
            itemViewHolder.item_mv_list_tv_num.setTextSize(22);
            itemViewHolder.item_mv_list_tv_num.setTextColor(
                    mContext.getResources().getColor(R.color.color_top_rank));
        } else {
            itemViewHolder.item_mv_list_tv_num.setTextSize(20);
            itemViewHolder.item_mv_list_tv_num.setTextColor(
                    mContext.getResources().getColor(R.color.text_color_white));
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {


        private final ImageView item_mv_list_iv_cover;
        private final TextView item_mv_list_tv_title;
        private final TextView item_mv_list_tv_num;
        private final TextView item_mv_list_tv_playCount;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_mv_list_iv_cover = itemView.findViewById(R.id.item_mv_list_iv_cover);
            item_mv_list_tv_title = itemView.findViewById(R.id.item_mv_list_tv_title);
            item_mv_list_tv_num = itemView.findViewById(R.id.item_mv_list_tv_num);
            item_mv_list_tv_playCount = itemView.findViewById(R.id.item_mv_list_tv_playCount);
        }

    }
}
