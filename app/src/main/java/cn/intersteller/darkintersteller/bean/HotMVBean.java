package cn.intersteller.darkintersteller.bean;

import java.util.List;

public class HotMVBean {

    /**
     * updateTime : 1535458945628
     * data : [{"id":10733097,"cover":"http://p1.music.126.net/5fDhiigtgB0YAnBPmemRLg==/109951163397371723.jpg","name":"颠倒","playCount":11505913,"briefDesc":"歌浴森自作曲《颠倒》MV公开！","desc":null,"artistName":"歌浴森","artistId":2945,"duration":0,"mark":0,"lastRank":1,"score":452201,"subed":false,"artists":[{"id":2945,"name":"歌浴森"}]},{"id":5965036,"cover":"http://p1.music.126.net/FuznAWoLFsX7TTBoQl4CLQ==/109951163425874499.jpg","name":"卡路里","playCount":12983709,"briefDesc":"火箭少女101献唱电影《西虹市首富》插曲","desc":null,"artistName":"火箭少女101","artistId":27693474,"duration":0,"mark":0,"lastRank":2,"score":263991,"subed":false,"artists":[{"id":27693474,"name":"火箭少女101"}]},{"id":5964088,"cover":"http://p1.music.126.net/DqPSK5okD55vrQIxEm_APQ==/109951163421540578.jpg","name":"左手指月","playCount":3376063,"briefDesc":"电视剧《香蜜沉沉烬如霜》片尾曲","desc":null,"artistName":"萨顶顶","artistId":9270,"duration":0,"mark":0,"lastRank":4,"score":110339,"subed":false,"artists":[{"id":9270,"name":"萨顶顶"}]},{"id":10759158,"cover":"http://p1.music.126.net/BimtxGgVAxyeQ6P86qpPbA==/109951163464270379.jpg","name":"浪得冒泡","playCount":4788857,"briefDesc":null,"desc":null,"artistName":"满舒克","artistId":188141,"duration":0,"mark":0,"lastRank":3,"score":97428,"subed":false,"artists":[{"id":188141,"name":"满舒克"}]},{"id":5955052,"cover":"http://p1.music.126.net/vtT1NtbXufK1FCTDGZ8MiA==/109951163368181315.jpg","name":"Wait A Minute","playCount":9158880,"briefDesc":"乐华七子NEXT首张音乐专辑主打歌","desc":null,"artistName":"乐华七子NEXT","artistId":15021290,"duration":0,"mark":0,"lastRank":5,"score":74577,"subed":false,"artists":[{"id":15021290,"name":"乐华七子NEXT"}]},{"id":5965135,"cover":"http://p1.music.126.net/vg0pmJimmqS1OP8lkSHpkw==/109951163433495844.jpg","name":"天地无霜","playCount":2579207,"briefDesc":"电视剧《香蜜沉沉烬如霜》主题曲","desc":null,"artistName":"杨紫","artistId":10368,"duration":0,"mark":0,"lastRank":7,"score":71922,"subed":false,"artists":[{"id":10368,"name":"杨紫"},{"id":12440042,"name":"邓伦"}]},{"id":10732128,"cover":"http://p1.music.126.net/G-nWoC063QDgg5699Bjanw==/109951163398869994.jpg","name":"隔壁泰山","playCount":4935494,"briefDesc":null,"desc":null,"artistName":"阿里郎","artistId":11015,"duration":0,"mark":0,"lastRank":6,"score":70338,"subed":false,"artists":[{"id":11015,"name":"阿里郎"}]},{"id":10769027,"cover":"http://p1.music.126.net/Rq_Wux_CuPyCSdjA5cc56w==/109951163517597834.jpg","name":"独家专访萨顶顶： 影视剧原声是我的新路口","playCount":382554,"briefDesc":null,"desc":null,"artistName":"萨顶顶","artistId":9270,"duration":0,"mark":0,"lastRank":-1,"score":66844,"subed":false,"artists":[{"id":9270,"name":"萨顶顶"}]},{"id":5958049,"cover":"http://p1.music.126.net/XkihyCylv229feA2aj6caA==/109951163366586966.jpg","name":"学猫叫","playCount":4579550,"briefDesc":null,"desc":null,"artistName":"小潘潘","artistId":12532848,"duration":0,"mark":0,"lastRank":8,"score":39849,"subed":false,"artists":[{"id":12532848,"name":"小潘潘"},{"id":790044,"name":"小峰峰"}]},{"id":5958062,"cover":"http://p1.music.126.net/f9g6pqhR2TlPQCXQmUcUwg==/109951163367657057.jpg","name":"消愁","playCount":6862195,"briefDesc":"毛不易热单《消愁》MV发布","desc":null,"artistName":"毛不易","artistId":12138269,"duration":0,"mark":0,"lastRank":9,"score":38207,"subed":false,"artists":[{"id":12138269,"name":"毛不易"}]}]
     * hasMore : true
     * code : 200
     */

    private long updateTime;
    private boolean hasMore;
    private int code;
    private List<DataBean> data;

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10733097
         * cover : http://p1.music.126.net/5fDhiigtgB0YAnBPmemRLg==/109951163397371723.jpg
         * name : 颠倒
         * playCount : 11505913
         * briefDesc : 歌浴森自作曲《颠倒》MV公开！
         * desc : null
         * artistName : 歌浴森
         * artistId : 2945
         * duration : 0
         * mark : 0
         * lastRank : 1
         * score : 452201
         * subed : false
         * artists : [{"id":2945,"name":"歌浴森"}]
         */

        private int id;
        private String cover;
        private String name;
        private int playCount;
        private String briefDesc;
        private Object desc;
        private String artistName;
        private int artistId;
        private int duration;
        private int mark;
        private int lastRank;
        private int score;
        private boolean subed;
        private List<ArtistsBean> artists;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public int getLastRank() {
            return lastRank;
        }

        public void setLastRank(int lastRank) {
            this.lastRank = lastRank;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public boolean isSubed() {
            return subed;
        }

        public void setSubed(boolean subed) {
            this.subed = subed;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public static class ArtistsBean {
            /**
             * id : 2945
             * name : 歌浴森
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}