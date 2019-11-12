package cn.intersteller.darkintersteller.test;

public class VolatileVisibleTest {

    public static volatile boolean isPraparedOK = false;

    public void testVolatileVisibleTest() throws InterruptedException {
        VolatileVisibleTest volatileVisibleTest = new VolatileVisibleTest();
        //线程1一直监听isPraparedOK的值，一旦为true就结束无限循环，打印”3开始正式工作“
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test: 1开始准备了");
                while (!volatileVisibleTest.isPraparedOK) {
                }
                System.out.println("test: 3开始正式工作");
            }
        }).start();
        Thread.sleep(1000);

        //线程2更改变量的值
        new Thread(new Runnable() {
            @Override
            public void run() {
                doPrepareOk();
            }
        }).start();


    }

    public static void doPrepareOk() {
        isPraparedOK = true;
        System.out.println("test: 2准备完毕");
    }

}
