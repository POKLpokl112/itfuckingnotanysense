package itfuckingnotanysense.onemorething;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.Semaphore;

import javax.xml.namespace.QName;

public class MyCollection {

    public static void main(String[] args) throws Exception {
        // testCall();
        // testInterrupted2();
        // testJoin();
        // testSemaphore();
        // testForkJoin();
        //testManyThread();
        testThreadLocal();
        
    }

    static void testThreadLocal(){
        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        new Thread(()->{
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            threadLocal1.set(1);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            threadLocal2.set(2);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());

        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            threadLocal1.set(1);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            threadLocal2.set(2);
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());

        }).start();

    }

    static class ThreadUnsafeExample {

        
        static int i = 0;

        private volatile int cnt = 0;

        public void add() {
            cnt++;
        }

        public int get() {
            return cnt;
        }
    }

    static void testManyThread() {
        final int threadSize = 1000;
        ThreadUnsafeExample ee = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(1000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                countDownLatch.countDown();
                ee.add();

                
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(ee.get());
    }

    static class ForkJoinExample extends RecursiveTask<Integer> {

        private final int threshold = 5;
        private int first;
        private int last;

        public ForkJoinExample(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        protected Integer compute() {
            int result = 0;
            if (last - first <= threshold) {
                // 任务足够小则直接计算
                for (int i = first; i <= last; i++) {
                    result += i;
                }
            } else {
                // 拆分成小任务
                int middle = first + (last - first) / 2;
                ForkJoinExample leftTask = new ForkJoinExample(first, middle);
                ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
                leftTask.fork();
                rightTask.fork();
                result = leftTask.join() + rightTask.join();
            }
            return result;
        }
    }

    static void testForkJoin() {
        long time = System.currentTimeMillis();
        ForkJoinExample forkJoinExample = new ForkJoinExample(0, 1000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(forkJoinExample);
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("1:" + (time - System.currentTimeMillis()));
        time = System.currentTimeMillis();
        int r = 0;
        for (int i = 0; i < 1001; i++) {
            r += i;

        }
        System.out.println(r);
        System.out.println(time - System.currentTimeMillis());

    }

    static void testSemaphore() {
        int c = 3;
        int total = 10;
        Semaphore s = new Semaphore(c);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < total; i++) {
            int j = i;
            executorService.submit(() -> {

                try {

                    s.acquire();
                    System.out.println(s.availablePermits() + "run:" + j);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    s.release();
                }

            });
        }
        executorService.shutdown();
    }

    static void testJoin() {
        Thread a = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("A");
        });

        Thread b = new Thread(() -> {
            try {
                a.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("B");
        });
        a.start();
        b.start();
    }

    static void testCall() throws Exception {
        Callable<Integer> c = new Callable<>() {
            @Override
            public Integer call() throws Exception {

                try {
                    Thread.sleep(2000);

                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.println(123);
                return 1;
            }
        };
        System.out.println(111);
        FutureTask<Integer> ft = new FutureTask<>(c);
        new Thread(ft).start();
        System.out.println(ft.get());
        System.out.println(444);
    }

    static void testInterrupted() throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread run");
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            };
        });
        t.start();
        t.interrupt();
        System.out.println("main end");
    }

    static void testInterrupted2() throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!interrupted()) {

                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Thread run");

            };

        };
        t.start();
        t.interrupt();
        System.out.println("main end");
    }

}
