package cn.intersteller.darkintersteller.bean;

import java.util.List;

public class RecomendBnnerBean {

    /**
     * banners : [{"imageUrl":"http://p1.music.126.net/EyWquSu46jsHkXhi00ESbQ==/109951163794133656.jpg","targetId":10851055,"adid":null,"targetType":1004,"titleColor":"red","typeTitle":"独家策划","url":null,"exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"10851055","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/7p6Pajazx4P5nqZekyekcA==/109951163793083521.jpg","targetId":1339422595,"adid":null,"targetType":1,"titleColor":"red","typeTitle":"新歌首发","url":null,"exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"1339422595","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/3H74Wqw7x1AORDCdIrZj3A==/109951163794688435.jpg","targetId":10850095,"adid":null,"targetType":1004,"titleColor":"red","typeTitle":"独家策划","url":null,"exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"10850095","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/VEc-aX-UuZb2LIyxsq9vNQ==/109951163793086212.jpg","targetId":1339537605,"adid":null,"targetType":1,"titleColor":"red","typeTitle":"独家","url":null,"exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"1339537605","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/QFR12azijZ9puxDaZ8GKxw==/109951163794830950.jpg","targetId":75186527,"adid":null,"targetType":10,"titleColor":"red","typeTitle":"独家","url":null,"exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"75186527","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/2-KuDRNw76tOzjrCgovYrQ==/109951163794656382.jpg","targetId":0,"adid":null,"targetType":3000,"titleColor":"blue","typeTitle":"数字专辑","url":"https://music.163.com/store/newalbum/detail?id=74794175","exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"0","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/uIheYwZj5iQk56mptunhSw==/109951163793077875.jpg","targetId":43308125,"adid":null,"targetType":1005,"titleColor":"blue","typeTitle":"独家策划","url":null,"exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"43308125","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/BJcqdaWhFtz-vJoOljww2A==/109951163793088272.jpg","targetId":0,"adid":null,"targetType":3000,"titleColor":"blue","typeTitle":"商城","url":"https://music.163.com/m/at/5c36a7fddaaddf9c8e34fe74","exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"0","program":null,"event":null,"video":null,"song":null},{"imageUrl":"http://p1.music.126.net/UWcMr9cQUh09yGIFWVeIwA==/109951163793081493.jpg","targetId":0,"adid":null,"targetType":3000,"titleColor":"blue","typeTitle":"活动","url":"https://music.163.com/m/at/5c34633ddaaddf9c8e34fbb3","exclusive":false,"monitorImpress":null,"monitorClick":null,"monitorType":null,"monitorImpressList":null,"monitorClickList":null,"monitorBlackList":null,"extMonitor":null,"extMonitorInfo":null,"adSource":null,"adLocation":null,"encodeId":"0","program":null,"event":null,"video":null,"song":null}]
     * code : 200
     */

    private int code;
    private List<BannersBean> banners;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean {
        /**
         * imageUrl : http://p1.music.126.net/EyWquSu46jsHkXhi00ESbQ==/109951163794133656.jpg
         * targetId : 10851055
         * adid : null
         * targetType : 1004
         * titleColor : red
         * typeTitle : 独家策划
         * url : null
         * exclusive : false
         * monitorImpress : null
         * monitorClick : null
         * monitorType : null
         * monitorImpressList : null
         * monitorClickList : null
         * monitorBlackList : null
         * extMonitor : null
         * extMonitorInfo : null
         * adSource : null
         * adLocation : null
         * encodeId : 10851055
         * program : null
         * event : null
         * video : null
         * song : null
         */

        private String imageUrl;
        private int targetId;
        private String typeTitle;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }
    }
}
