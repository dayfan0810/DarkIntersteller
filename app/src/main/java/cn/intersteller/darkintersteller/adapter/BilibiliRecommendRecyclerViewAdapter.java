package cn.intersteller.darkintersteller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.intersteller.darkintersteller.bean.BilibiliRecommendInfo;

public class BilibiliRecommendRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    public BilibiliRecommendRecyclerViewAdapter(Context context, List<BilibiliRecommendInfo.ResultBean> itemBeansList, int layoutId) {
        super(context, itemBeansList, layoutId);
    }

    @Override
    protected void onBind(BaseRecyclerViewHolder holder, Object o, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
