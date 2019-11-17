package cn.intersteller.darkintersteller.bean;

import java.util.List;

/**
 * creator: jerry
 * time: 2019/11/17
 */
public class SearchSuggestBean {


    /**
     * result : {"allMatch":[{"keyword":"海阔天空","type":1,"alg":"alg_psug_a","lastKeyword":"海阔天空"},{"keyword":"海阔天空抖音版","type":1,"alg":"alg_psug_a","lastKeyword":"海阔天空"},{"keyword":"海阔天空国语版","type":1,"alg":"alg_psug_a","lastKeyword":"海阔天空"},{"keyword":"海阔天空dj","type":1,"alg":"alg_psug_a","lastKeyword":"海阔天空"},{"keyword":"海阔天空伴奏","type":1,"alg":"alg_psug_a","lastKeyword":"海阔天空"},{"keyword":"海阔天空亲爱的热爱的","type":1,"alg":"alg_psug_a","lastKeyword":"海阔天空"}]}
     * code : 200
     */

    private ResultBean result;
    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        private List<AllMatchBean> allMatch;

        public List<AllMatchBean> getAllMatch() {
            return allMatch;
        }

        public void setAllMatch(List<AllMatchBean> allMatch) {
            this.allMatch = allMatch;
        }

        public static class AllMatchBean {
            /**
             * keyword : 海阔天空
             * type : 1
             * alg : alg_psug_a
             * lastKeyword : 海阔天空
             */

            private String keyword;
            private int type;
            private String alg;
            private String lastKeyword;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public String getLastKeyword() {
                return lastKeyword;
            }

            public void setLastKeyword(String lastKeyword) {
                this.lastKeyword = lastKeyword;
            }
        }
    }
}
