package test;

public class TestThreadLocal {
    public static void main(String[] args) {
//        testNormalClass(10);
        testClassWithThreadLocal(10);
    }

    static void testNormalClass(int size) {
        NormalClass2 cls = new NormalClass2();
        for (int i = 0; i < size; i++) {
            Thread t = new Thread(cls);
            t.start();
        }
    }

    static void testClassWithThreadLocal(int size) {
        ClassWithThreadLocal cls = new ClassWithThreadLocal();
        for (int i = 0; i < size; i++) {
            Thread t = new Thread(cls);
            t.start();
        }
    }
}

class NormalClass2 implements Runnable {
    private static StringBuilder sb = new StringBuilder();

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sb.append(1);
        System.out.println("thread name: " + Thread.currentThread().getName() + ", sb: " + sb+ ", sb.length: " + sb.length());
    }
}

class NormalClass implements Runnable {
    private static int count = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
        System.out.println("thread name: " + Thread.currentThread().getName() + ", count: " + count);
    }
}

class ClassWithThreadLocal implements Runnable {
    private static ThreadLocal<StringBuilder> sb = ThreadLocal.withInitial(() -> new StringBuilder());

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sb.set(sb.get().append(1));
        System.out.println("thread name: " + Thread.currentThread().getName() + ", sb: " + sb.get() + ", sb.length: " + sb.get().length());
    }

}