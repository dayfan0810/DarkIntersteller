package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.CloudPanBean;

public class CloudPanRecyclerViewAdapter extends RecyclerView.Adapter<CloudPanRecyclerViewAdapter.ItemViewHolder> {
    Context mContext;
    List<CloudPanBean.DataBean> mCloudPansBeans;
    List<CloudPanBean.DataBean.SimpleSongBean> mSimpleSongBeans;

    public CloudPanRecyclerViewAdapter(Context context, List<CloudPanBean.DataBean> mCloudPansBeans, List<CloudPanBean.DataBean.SimpleSongBean> mSimpleSongBeans, RecyclerView recyclerView, final LinearLayoutManager manager) {
        this.mCloudPansBeans = mCloudPansBeans;
        this.mSimpleSongBeans = mSimpleSongBeans;
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

    public CloudPanBean.DataBean getItem(int position) {
        return mCloudPansBeans == null ? null : mCloudPansBeans.get(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cloud_pan_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mCloudPansBeans.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        CloudPanBean.DataBean cloudPanBean = mCloudPansBeans.get(position);
        CloudPanBean.DataBean.SimpleSongBean simpleSongBean = mSimpleSongBeans.get(position);
        if (cloudPanBean == null && simpleSongBean == null) {
            Log.i("deng", "onBindViewHolder  =  null");
            return;
        }
        holder.tv_item_cloud_pan_num.setText(String.valueOf(position + 1));
        holder.tv_item_cloud_pan_song_name.setText(cloudPanBean.getSongName()+"      "+cloudPanBean.getArtist());
        holder.tv_item_cloud_pan_al_name.setText(cloudPanBean.getAlbum());
//        holder.tv_item_cloud_pan_al_name.setText(simpleSongBean.getName().equals("null") ? "": simpleSongBean.getName());
//        holder.iv_item_cloud_pan.setText(mvBean.getPlayCount() + "次");
//        holder.iv_item_cloud_pan.setText(mvBean.getName());
//        setSortNumTextSize(holder, position);
//        if (mOnItemClickListener != null) {
//            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).item_mv_list_iv_cover, mvBean.getCover());
//            ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(((ItemViewHolder) holder).itemView, position);
//                }
//            });
//
//            ((ItemViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mOnItemClickListener.onItemLongClick(((ItemViewHolder) holder).itemView, position);
//                    return false;
//                }
//            });
//        }
    }

//    private void setSortNumTextSize(ItemViewHolder itemViewHolder, int position) {
//        if (position == 0) {
//            itemViewHolder.item_mv_list_tv_num.setTextSize(26);
//            itemViewHolder.item_mv_list_tv_num.setTextColor(
//                    mContext.getResources().getColor(R.color.color_top_rank));
//        } else if (position == 1) {
//            itemViewHolder.item_mv_list_tv_num.setTextSize(24);
//            itemViewHolder.item_mv_list_tv_num.setTextColor(
//                    mContext.getResources().getColor(R.color.color_top_rank));
//        } else if (position == 2) {
//            itemViewHolder.item_mv_list_tv_num.setTextSize(22);
//            itemViewHolder.item_mv_list_tv_num.setTextColor(
//                    mContext.getResources().getColor(R.color.color_top_rank));
//        } else {
//            itemViewHolder.item_mv_list_tv_num.setTextSize(20);
//            itemViewHolder.item_mv_list_tv_num.setTextColor(
//                    mContext.getResources().getColor(R.color.text_color_white));
//        }
//    }


    class ItemViewHolder extends RecyclerView.ViewHolder {


        private final ImageView iv_item_cloud_pan_has_mv;
        private final ImageView iv_item_cloud_pan_singlesong_menu;
        private final TextView tv_item_cloud_pan_num;
        private final TextView tv_item_cloud_pan_song_name;
        private final TextView tv_item_cloud_pan_al_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_cloud_pan_num = itemView.findViewById(R.id.tv_item_cloud_pan_num);
            tv_item_cloud_pan_song_name = itemView.findViewById(R.id.tv_item_cloud_pan_song_name);
            tv_item_cloud_pan_al_name = itemView.findViewById(R.id.tv_item_cloud_pan_al_name);
            iv_item_cloud_pan_has_mv = itemView.findViewById(R.id.iv_item_cloud_pan_has_mv);
            iv_item_cloud_pan_singlesong_menu = itemView.findViewById(R.id.iv_item_cloud_pan_singlesong_menu);
        }

    }
}
