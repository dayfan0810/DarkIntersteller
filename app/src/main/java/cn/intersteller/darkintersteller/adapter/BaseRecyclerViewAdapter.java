package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    Context mContext;
    List<T> mItemBeansList;
    int layoutId;

    public BaseRecyclerViewAdapter(Context context, List<T> itemBeansList, int layoutId) {
        this.mContext = context;
        this.mItemBeansList = itemBeansList;
        this.layoutId = layoutId;

    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseRecyclerViewHolder.getHolder(mContext, parent, layoutId);
    }

    public void setData(List<T> itemBeansList) {
        this.mItemBeansList = itemBeansList;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        onBind(holder, mItemBeansList.get(position), position);
    }

    protected abstract void onBind(BaseRecyclerViewHolder holder, T t, int position);


}
