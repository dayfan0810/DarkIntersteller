package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    SparseArray<View> views;


    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }


    public static <T extends BaseRecyclerViewHolder> T getHolder(Context context, ViewGroup parent, int layoutId) {
        return (T) new BaseRecyclerViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    //根据Item中的控件Id获取控件
    public <T extends View> T getView(int viewId) {
        View childreView = views.get(viewId);
        if (childreView == null) {
            childreView = itemView.findViewById(viewId);
            views.put(viewId, childreView);
        }
        return (T) childreView;
    }

    //根据Item中的控件Id向控件添加事件监听
    public BaseRecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
