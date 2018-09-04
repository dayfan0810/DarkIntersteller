package cn.intersteller.darkintersteller.utils;

import java.util.Comparator;

import cn.intersteller.darkintersteller.bean.HotMVBean;

public class MVSortUtils implements Comparator<HotMVBean.DataBean> {

    @Override
    public int compare(HotMVBean.DataBean o1, HotMVBean.DataBean o2) {
        int playCount1 = o1.getDuration();
        int playCount2 = o1.getDuration();
        return playCount1-playCount2;
    }
}
