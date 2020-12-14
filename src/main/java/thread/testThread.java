package thread;

/**
 * @Author laijinhan
 * @date 2020/12/13 下午10:57
 */

import java.util.concurrent.*;

/**
 * 线程实现方法一，1，继承Thread，2.重写run方法，3.start执行线程
 */
public class testThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("线程实现方式一，我在执行线程：" + i);
		}
	}

	public static void main(String[] args) {
		new testThread().start();
	}
}

/**
 * 创建线程方式二：实现Runnable接口；重写run方法；
 * 执行线程需要丢入runnable 接口的实现类，调用satrt方法
 * 跟方式一实现是一样的，Thread 线程类也实现了Runnable接口，但是可以避免单继承的局限
 */
class testThread1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("线程实现方式二，我在执行线程：" + i);
		}
	}

	public static void main(String[] args) {
		testThread1 testThread1 = new testThread1();
		new Thread(testThread1).start();
	}

}

/**
 * 实现线程方式三，实现Callable接口
 * 继承Callable接口，重写call方法，创建执行服务ExecutorService、提交执行、获取结果、关闭服务
 */
class testThread2 implements Callable {

	@Override
	public Boolean call() throws Exception {
		for (int i = 0; i < 20; i++) {
			System.out.println("线程实现方式三，我在执行线程：" + i);
		}
		return true;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		testThread2 testThread2 = new testThread2();
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future<?> submit = executorService.submit(testThread2);
		Object o = submit.get();
		executorService.shutdown();
	}
}