import cn.dbj.knowledge.design.behavioral.state.Context;
import cn.dbj.knowledge.design.behavioral.state.ShareContext;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestState {

    @Test
    public void state() {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
    }

    @Test
    public void flyweightState() {
        ShareContext context = new ShareContext();
        context.handle();
        context.handle();
        context.handle();
    }

    @Test
    public void a() throws Exception{
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.DAYS, queue);
        for (int i = 0; i < 6; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {

                }
            });
        }

        Thread.sleep(1000);
        System.out.println(queue.size());

    }

}
