package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.bean.SearchSuggestBean;

public class SearchSuggestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<SearchSuggestBean.ResultBean.AllMatchBean> mAllMatchBeans;

    public SearchSuggestRecyclerViewAdapter(Context context, List<SearchSuggestBean.ResultBean.AllMatchBean> mAllMatchBeans, RecyclerView recyclerView, final LinearLayoutManager manager) {
        this.mAllMatchBeans = mAllMatchBeans;
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
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    //供外部fragment去设置回调,回调在onBindViewHolder中触发
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public SearchSuggestBean.ResultBean.AllMatchBean getItem(int position) {
        return mAllMatchBeans == null ? null : mAllMatchBeans.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("dengsearch5", "mAllMatchBeans.size  = " + mAllMatchBeans.size());
        if (viewType == VIEW_TYPE_NO_DATA) {
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.include_load_fail, parent, false);
            return new EmptyViewHolder(view);
        } else if (viewType == VIEW_TYPE_HAS_SUGGEST_SEARCHER_DATA) {
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.item_search_suggest_of_rv, parent, false);
            return new ItemViewHolder(view);
        } else {
            //noinspection ConstantConditions
            return null;
        }
    }


    @Override
    public int getItemCount() {
        return mAllMatchBeans.size() > 0 ? mAllMatchBeans.size() : 1;
    }


    @Override
    public int getItemViewType(int position) {

        if (mAllMatchBeans.size() <= 0) {
            return VIEW_TYPE_NO_DATA;
        }
        return super.getItemViewType(position);
    }

    private static final int VIEW_TYPE_NO_DATA = -1;
    private static final int VIEW_TYPE_HAS_SUGGEST_SEARCHER_DATA = 0;


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == VIEW_TYPE_NO_DATA) {
            Log.i("dengsearch5", "66666666666mAllMatchBeans.size  = " + mAllMatchBeans.size());
            ((EmptyViewHolder) holder).tv_load_fail_text.setText("没有搜到结果");
        } else {
            SearchSuggestBean.ResultBean.AllMatchBean mAllMatchBean = mAllMatchBeans.get(position);
            ((ItemViewHolder) holder).tv_search_suggest_of_rv.setText(mAllMatchBean.getKeyword());
            if (mOnItemClickListener != null) {
                ((ItemViewHolder) holder).itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(((ItemViewHolder) holder).itemView, position));
            }
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_search_suggest_of_rv;

        ItemViewHolder(View itemView) {
            super(itemView);
            tv_search_suggest_of_rv = itemView.findViewById(R.id.tv_search_suggest_of_rv);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_load_fail_text;

        EmptyViewHolder(View itemView) {
            super(itemView);
            tv_load_fail_text = itemView.findViewById(R.id.tv_load_fail_text);

        }
    }
}
