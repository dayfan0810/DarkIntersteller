package cn.intersteller.darkintersteller.bean;

import java.util.List;

public class LoginResultBean {
    /**
     登录后显示的json
     http://39.108.131.225:3000/login?email=mr_day@163.com&password=razrjay1234
     */


    /**
     * loginType : 0
     * code : 200
     * account : {"id":1188120,"userName":"1_15527907583","type":1,"status":0,"whitelistAuthority":0,"createTime":0,"salt":"[B@3380737c","tokenVersion":8,"ban":0,"baoyueVersion":1,"donateVersion":0,"vipType":10,"viptypeVersion":1568725396058,"anonimousUser":false}
     * profile : {"backgroundUrl":"https://p1.music.126.net/6DMX4zA5KiOo0ogiy2FW_Q==/109951163715930569.jpg","avatarImgIdStr":"109951163714288219","backgroundImgIdStr":"109951163715930569","detailDescription":"","followed":false,"description":"","defaultAvatar":false,"avatarUrl":"https://p4.music.126.net/MpL69azafwespE5rEDduMQ==/109951163714288219.jpg","vipType":10,"gender":1,"accountStatus":0,"djStatus":0,"userId":1188120,"avatarImgId":109951163714288220,"userType":0,"backgroundImgId":109951163715930580,"nickname":"razrday","birthday":690134400000,"city":1010000,"province":1000000,"mutual":false,"remarkName":null,"expertTags":null,"authStatus":0,"experts":{},"signature":"安静的程序员","authority":0,"avatarImgId_str":"109951163714288219","followeds":7,"follows":26,"eventCount":16,"playlistCount":35,"playlistBeSubscribedCount":17}
     * bindings : [{"url":"","expiresIn":2147483647,"bindingTime":1400601611934,"refreshTime":1400601611,"expired":false,"userId":1188120,"tokenJsonStr":"{\"countrycode\":\"\",\"cellphone\":\"15527907583\",\"hasPassword\":true}","id":8149815,"type":1}]
     */

    private int loginType;
    private int code;
    private AccountBean account;
    private ProfileBean profile;
    private List<BindingsBean> bindings;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public List<BindingsBean> getBindings() {
        return bindings;
    }

    public void setBindings(List<BindingsBean> bindings) {
        this.bindings = bindings;
    }

    public static class AccountBean {
        /**
         * id : 1188120
         * userName : 1_15527907583
         * type : 1
         * status : 0
         * whitelistAuthority : 0
         * createTime : 0
         * salt : [B@3380737c
         * tokenVersion : 8
         * ban : 0
         * baoyueVersion : 1
         * donateVersion : 0
         * vipType : 10
         * viptypeVersion : 1568725396058
         * anonimousUser : false
         */

        private int id;
        private String userName;
        private int type;
        private int status;
        private int whitelistAuthority;
        private int createTime;
        private String salt;
        private int tokenVersion;
        private int ban;
        private int baoyueVersion;
        private int donateVersion;
        private int vipType;
        private long viptypeVersion;
        private boolean anonimousUser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWhitelistAuthority() {
            return whitelistAuthority;
        }

        public void setWhitelistAuthority(int whitelistAuthority) {
            this.whitelistAuthority = whitelistAuthority;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public int getTokenVersion() {
            return tokenVersion;
        }

        public void setTokenVersion(int tokenVersion) {
            this.tokenVersion = tokenVersion;
        }

        public int getBan() {
            return ban;
        }

        public void setBan(int ban) {
            this.ban = ban;
        }

        public int getBaoyueVersion() {
            return baoyueVersion;
        }

        public void setBaoyueVersion(int baoyueVersion) {
            this.baoyueVersion = baoyueVersion;
        }

        public int getDonateVersion() {
            return donateVersion;
        }

        public void setDonateVersion(int donateVersion) {
            this.donateVersion = donateVersion;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public long getViptypeVersion() {
            return viptypeVersion;
        }

        public void setViptypeVersion(long viptypeVersion) {
            this.viptypeVersion = viptypeVersion;
        }

        public boolean isAnonimousUser() {
            return anonimousUser;
        }

        public void setAnonimousUser(boolean anonimousUser) {
            this.anonimousUser = anonimousUser;
        }
    }

    public static class ProfileBean {
        /**
         * backgroundUrl : https://p1.music.126.net/6DMX4zA5KiOo0ogiy2FW_Q==/109951163715930569.jpg
         * avatarImgIdStr : 109951163714288219
         * backgroundImgIdStr : 109951163715930569
         * detailDescription :
         * followed : false
         * description :
         * defaultAvatar : false
         * avatarUrl : https://p4.music.126.net/MpL69azafwespE5rEDduMQ==/109951163714288219.jpg
         * vipType : 10
         * gender : 1
         * accountStatus : 0
         * djStatus : 0
         * userId : 1188120
         * avatarImgId : 109951163714288220
         * userType : 0
         * backgroundImgId : 109951163715930580
         * nickname : razrday
         * birthday : 690134400000
         * city : 1010000
         * province : 1000000
         * mutual : false
         * remarkName : null
         * expertTags : null
         * authStatus : 0
         * experts : {}
         * signature : 安静的程序员
         * authority : 0
         * avatarImgId_str : 109951163714288219
         * followeds : 7
         * follows : 26
         * eventCount : 16
         * playlistCount : 35
         * playlistBeSubscribedCount : 17
         */

        private String backgroundUrl;
        private String avatarImgIdStr;
        private String backgroundImgIdStr;
        private String detailDescription;
        private boolean followed;
        private String description;
        private boolean defaultAvatar;
        private String avatarUrl;
        private int vipType;
        private int gender;
        private int accountStatus;
        private int djStatus;
        private int userId;
        private long avatarImgId;
        private int userType;
        private long backgroundImgId;
        private String nickname;
        private long birthday;
        private int city;
        private int province;
        private boolean mutual;
        private Object remarkName;
        private Object expertTags;
        private int authStatus;
        private ExpertsBean experts;
        private String signature;
        private int authority;
        private String avatarImgId_str;
        private int followeds;
        private int follows;
        private int eventCount;
        private int playlistCount;
        private int playlistBeSubscribedCount;

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getAvatarImgIdStr() {
            return avatarImgIdStr;
        }

        public void setAvatarImgIdStr(String avatarImgIdStr) {
            this.avatarImgIdStr = avatarImgIdStr;
        }

        public String getBackgroundImgIdStr() {
            return backgroundImgIdStr;
        }

        public void setBackgroundImgIdStr(String backgroundImgIdStr) {
            this.backgroundImgIdStr = backgroundImgIdStr;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public ExpertsBean getExperts() {
            return experts;
        }

        public void setExperts(ExpertsBean experts) {
            this.experts = experts;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }

        public int getFolloweds() {
            return followeds;
        }

        public void setFolloweds(int followeds) {
            this.followeds = followeds;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public int getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public static class ExpertsBean {
        }
    }

    public static class BindingsBean {
        /**
         * url :
         * expiresIn : 2147483647
         * bindingTime : 1400601611934
         * refreshTime : 1400601611
         * expired : false
         * userId : 1188120
         * tokenJsonStr : {"countrycode":"","cellphone":"15527907583","hasPassword":true}
         * id : 8149815
         * type : 1
         */

        private String url;
        private int expiresIn;
        private long bindingTime;
        private int refreshTime;
        private boolean expired;
        private int userId;
        private String tokenJsonStr;
        private int id;
        private int type;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public long getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(long bindingTime) {
            this.bindingTime = bindingTime;
        }

        public int getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(int refreshTime) {
            this.refreshTime = refreshTime;
        }

        public boolean isExpired() {
            return expired;
        }

        public void setExpired(boolean expired) {
            this.expired = expired;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTokenJsonStr() {
            return tokenJsonStr;
        }

        public void setTokenJsonStr(String tokenJsonStr) {
            this.tokenJsonStr = tokenJsonStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}
