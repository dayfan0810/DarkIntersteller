package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.MyPlayListBean;
import cn.intersteller.darkintersteller.utils.ImageLoaderUtils;

public class MyPlayListRecyclerViewAdapter extends RecyclerView.Adapter<MyPlayListRecyclerViewAdapter.ItemViewHolder> {
    Context mContext;
    List<MyPlayListBean.PlaylistBean> mPlaylistBeans;

    public MyPlayListRecyclerViewAdapter(Context context, List<MyPlayListBean.PlaylistBean> mPlaylistBeans, RecyclerView recyclerView, final LinearLayoutManager manager) {
        this.mPlaylistBeans = mPlaylistBeans;
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

    public MyPlayListBean.PlaylistBean getItem(int position) {
        return mPlaylistBeans == null ? null : mPlaylistBeans.get(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_play_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mPlaylistBeans.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        MyPlayListBean.PlaylistBean playlistBean = mPlaylistBeans.get(position);
        if (playlistBean == null) {
            Log.i("deng", "onBindViewHolder  =  null");
            return;
        }
        holder.tv_item_my_playlist_name.setText(playlistBean.getName());
        holder.tv_item_my_playlist_trackCount.setText(playlistBean.getTrackCount() + "首");
        ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).tv_item_my_playlist_coverImgUrl, playlistBean.getCoverImgUrl());
        Log.i("dengplaylist", "coverImgUrl = " + playlistBean.getCoverImgUrl());

        if (mOnItemClickListener != null) {
            ((ItemViewHolder) holder).itemView.setOnClickListener(view -> {
                mOnItemClickListener.onItemClick(((ItemViewHolder) holder).itemView, position);
            });

            ((ItemViewHolder) holder).itemView.setOnLongClickListener(view -> {
                mOnItemClickListener.onItemLongClick(((ItemViewHolder) holder).itemView, position);
                return false;
            });
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {


        private final ImageView tv_item_my_playlist_coverImgUrl;
        private final TextView tv_item_my_playlist_name;
        private final TextView tv_item_my_playlist_trackCount;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_my_playlist_coverImgUrl = itemView.findViewById(R.id.tv_item_my_playlist_coverImgUrl);
            tv_item_my_playlist_name = itemView.findViewById(R.id.tv_item_my_playlist_name);
            tv_item_my_playlist_trackCount = itemView.findViewById(R.id.tv_item_my_playlist_trackCount);
        }

    }
}
