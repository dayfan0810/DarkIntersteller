package cn.intersteller.darkintersteller.test;

import android.content.Context;
import android.content.Intent;

public class DealThread implements Runnable {
    public DealThread(Context context) {
        mContext = context;
    }

    public String username;
    public Context mContext;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        int i = 0;
        int j = 0;
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
					Thread.sleep(3000);
                    Intent intent = new Intent(mContext, ImmersionTestActivity.class);
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("按照lock1->lock2的顺序执行了");
                }
            }
        }
        if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    System.out.println("bbb一一一一一一一一一一一一" + j++);

					Thread.sleep(3000);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按照lock2->lock1的顺序执行了");
                }
            }
        }
    }

}