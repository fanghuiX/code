package JavaConcurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by author on 2021/4/10 14:34
 */
public class ConcurrencyTest {

    public static final ThreadPoolExecutor excutor = new ThreadPoolExecutor(2, 3,
            60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));

    public static ThreadLocal<String> fh = new ThreadLocal<>();

    public static void main(String[] args) {
        for(int i=0; i<8; i++) {
            excutor.execute(new Runnable() {
                @Override
                public void run() {
                    fh.set(Thread.currentThread().getName() + "_fanghui");
                    System.out.println(Thread.currentThread().getName() + " START!!!!!!   " + fh.get());
                }
            });
        }
    }

}
