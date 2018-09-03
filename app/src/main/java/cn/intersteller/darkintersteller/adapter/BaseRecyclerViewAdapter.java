package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {
    Context context;
    List<T> itemBeansList;

    public BaseRecyclerViewAdapter(Context context, List<T> itemBeansList) {
        this.context = context;
        this.itemBeansList = itemBeansList;
    }

//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return BaseRecyclerViewHolder.getHolder(parent, viewType);
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBind();
    }

    protected abstract void onBind();
}
