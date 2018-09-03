package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }


//    public static <T extends BaseRecyclerViewHolder> T getHolder(Context context, ViewGroup parent){
//        return  (T) new BaseHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
//    }
}
