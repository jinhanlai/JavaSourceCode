package thread.jucstudy;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Author laijinhan
 * @date 2020/12/20 下午9:37
 */


public class TestThreadPool {
	@Test
	public void testPool(){
		// 线程池的三大方法
		// ExecutorService executorService = Executors.newSingleThreadExecutor();// 单个线程
		ExecutorService executorService = Executors.newFixedThreadPool(5);// 固定5个线程池
		// Executors.newCachedThreadPool();// 可伸缩的线程池
		ExecutorService  executorService1=new ThreadPoolExecutor(
				2,
				3,
				3,
				TimeUnit.SECONDS,
				new LinkedBlockingDeque<>(3),
				new ThreadPoolExecutor.AbortPolicy()
		);
		//使用
		try {
			for(int i=0;i<10;i++){
				executorService.execute(()->{
					System.out.println(Thread.currentThread().getName()+"do");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}
}
