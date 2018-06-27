package cn.intersteller.darkintersteller.utils;

public class SortUtils {
    static class SortThread extends Thread{
        int sortId;

        public SortThread(int sortId) {
            this.sortId = sortId;
        }

        @Override
        public void run() {
            switch (sortId){
                case 1:
                    //1 == 冒泡排序
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
    }
}
