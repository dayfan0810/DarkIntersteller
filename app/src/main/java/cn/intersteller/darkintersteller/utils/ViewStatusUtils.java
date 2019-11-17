package cn.intersteller.darkintersteller.utils;

import android.view.View;

/*
搜索界面有3中状态,对应3个不同的布局
正常搜到结果
网速慢,正在努力搜
搜不到结果
 */

public class ViewStatusUtils {
    public static void changeViewState(View loadSuccess, View loading, View loadFail, LoadStateEnum state) {
        switch (state) {
            case LOADING:
                loadSuccess.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loadFail.setVisibility(View.GONE);
                break;
            case LOAD_SUCCESS:
            case LOAD_CACHE_OR_IDLE:
                loadSuccess.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                loadFail.setVisibility(View.GONE);
                break;
            case LOAD_FAIL:
                loadSuccess.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                loadFail.setVisibility(View.VISIBLE);
                break;
        }
    }

    public enum LoadStateEnum {
        LOADING,// 加载中
        LOAD_SUCCESS,// 加载成功
        LOAD_FAIL,// 加载失败
        LOAD_CACHE_OR_IDLE// 无网络加载本地缓存或者此页面暂时不需要加载就是空的(比如搜索界面刚打开时)
    }
}