package cn.intersteller.darkintersteller.sort;

public class BubbleSort extends BubbleSortSwapThread {

    public interface a {
        void a(int[] iArr, int i);

        void b(int i, int i2);

        void c(int i, int i2);

        void g();
    }

    public void a(int[] iArr, a aVar) {
        synchronized (this) {
            int length = iArr.length;
            this.a = Thread.currentThread();
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2;
                for (i2 = length - 1; i2 > i; i2--) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    if (iArr[i2] < iArr[i2 - 1]) {
                        i3++;
                        a(iArr, i2, i2 - 1);
                        a(aVar, i2, i2 - 1);
                    } else {
                        b(aVar, i2, i2 - 1);
                    }
                    a();
                }
                a(aVar, iArr, i);
                i++;
                i2 = i3;
            }
            a(aVar);
        }
    }

    private void a(a aVar, int i, int i2) {
        b.b(new b(this, aVar, i, i2));
    }

    private void b(a aVar, int i, int i2) {
        b.b(new c(this, aVar, i, i2));
    }

    private void a(a aVar, int[] iArr, int i) {
        b.b(new d(this, aVar, iArr, i));
    }

    private void a(a aVar) {
        b.b(new e(this, aVar));
    }

}
