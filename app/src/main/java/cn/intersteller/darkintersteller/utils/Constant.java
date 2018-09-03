package cn.intersteller.darkintersteller.utils;

public class Constant {
    //新闻头条
    //http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    public static final String URL_TOPNEWS = "http://v.juhe.cn/toutiao/index?type=top&key=9c98897f8c0ef2f97ce13ef48f0c6cc1--错误的";
    //网易云音乐接口
    public static final String NETEASEBASE = "https://netease.api.zzsun.cc/";
    //MV详情,根据Item的Id
    public static final String NETEASE_MV_ID = "mv?mvid=";
    //mv 排行
    public static final String NETEASE_TOP_MV = NETEASEBASE + "top/mv?limit=99";

    //哔哩哔哩
    public static final String BILIBILI_BASE_URL = "http://app.bilibili.com/";

    /**
     * 首页推荐数据
     */
    public static final String BILIBILI_RECOMMEND = BILIBILI_BASE_URL + "x/show/old?platform=android&device=&build=412001";

    /**
     * 首页推荐banner
     */
    public static final String BILIBILI_BANNER = BILIBILI_BASE_URL + "x/banner?plat=4&build=411007&channel=bilih5";

}
