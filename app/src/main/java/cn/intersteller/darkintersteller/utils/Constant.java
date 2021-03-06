package cn.intersteller.darkintersteller.utils;

import cn.intersteller.darkintersteller.MyApplication;

public class Constant {
    //sharedPreference
    public static final String LOGIN_USER_ID = "login_user_id";


    //新闻头条
    //http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    public static final String URL_TOPNEWS = "http://v.juhe.cn/toutiao/index?type=top&key=9c98897f8c0ef2f97ce13ef48f0c6cc1";
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static boolean isWifi = NetworkUtils.isConnectWifi(MyApplication.getInstance());
    //网易云音乐接口
//    public static final String NETEASEBASE = "http://musicapi.leanapp.cn/";
    public static final String NETEASEBASE = isWifi ? "http://39.108.131.225:3000/" : "http://39.108.131.225:3000/";

    //MV详情,根据Item的Id
    public static final String NETEASE_MV_ID = "mv?mvid=";
    //mv 排行
    public static final String NETEASE_TOP_MV = NETEASEBASE + "top/mv?limit=99";
    //banner
    public static final String NETEASE_BANNER = NETEASEBASE + "banner";
    //推荐 mv
    public static final String NETEASE_PERSONALIZED = NETEASEBASE + "/personalized/mv";
    //邮箱登录:/login?email=xxx@163.com&password=yyy
    public static final String NETEASE_LOGIN = NETEASEBASE + "login?email=xxx@163.com&password=yyy";
    //云盘
    public static final String NETEASE_CLOUD_PAN = NETEASEBASE + "user/cloud?limit=100";
    //我的歌单
    public static final String NETEASE_MYPLAYLIST = NETEASEBASE + "user/playlist?uid=";

    //搜索建议http://39.108.131.225:3000/search/suggest?keywords=林俊杰
    public static final String NETEASE_SEARCH_SUGGEST = NETEASEBASE + "search/suggest?keywords=";
    //搜索视频  http://39.108.131.225:3000/search?keywords=林俊杰&type=1014
    public static final String NETEASE_SEARCH_VIDEO = NETEASEBASE + "search?keywords=";


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


    /*
     * Cnbeta
     */
    public static final String CNBEA_BASE_URL = "https://www.cnbeta.com";
    public static final String CNBETA_NEWS_LIST_URL = CNBEA_BASE_URL + "/home/more";

    /*
    jin10
     */
    public static final String JIN10_BASE_URL = "https://www.jin10.com/";

    /*
    美港电讯
     */
    public static final String USHKNEWS_BASE_URL = "http://www.ushknews.com/";
}
