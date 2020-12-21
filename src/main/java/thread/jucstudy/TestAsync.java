package thread.jucstudy;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author laijinhan
 * @date 2020/12/21 下午9:44
 */


public class TestAsync {
	@Test
	public void test() throws ExecutionException, InterruptedException {
		/**
		 * 测试异步通信，类似与ajax
		 */
		// 没有返回值
		CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"run async");
		});
		System.out.println(111);
		completableFuture.get();
	}
	@Test
	public void test1() throws ExecutionException, InterruptedException {
		/**
		 * 测试异步通信，类似与ajax
		 * 有返回值
		 */
		CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 123;
		});
		System.out.println(456);
		completableFuture.whenComplete((t,u)->{
			System.out.println("t=>"+t);
			System.out.println("u=>"+u);
		}).exceptionally(e->{
			System.out.println(e.getMessage());
			return 789;
		}).get();
	}
}
