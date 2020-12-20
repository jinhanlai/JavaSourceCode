package thread;

import com.sun.tools.javadoc.Start;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author laijinhan
 * @date 2020/12/19 下午10:16
 */


public class CollectionTest {
	@Test
	public void testUnsafeCollection(){
		// List<String> list=new Vector<>();
		// List<String> list=new ArrayList<>(); 不安全的
		// List<String> list=Collections.synchronizedList(new ArrayList<>());
		List<String> list=new CopyOnWriteArrayList<>();

		for(int i=0;i<10;i++){
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,5));
				System.out.println(list);
			},String.valueOf(i)).start();

		}
	}
	@Test
	public void testHashMap(){
		Map <String,String> maps=new HashMap<>();
		for(int i=0;i<16;i++){
			System.out.println(i);
			maps.put(String.valueOf(i),String.valueOf(i));
		}
	}
	@Test
	public void testCountDownLatch() throws InterruptedException {
		// 定义一个计数器
		CountDownLatch countDownLatch=new CountDownLatch(6);
		for(int i=0;i<6;i++){
			new Thread(()->{
				countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName()+"go out");
			},String.valueOf(i)).start();
		}
		countDownLatch.await(); // 直到计数器为0，才执行下面的代码。
		System.out.println("close door");
	}
	@Test
	public void testCycliBairer(){
		// 第一个参数是数字，第二个参数是Runable接口的线程，当计数值达到第一个参数时就执行该线程
		CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
			System.out.println("召唤神龙");
		});
		for(int i=0;i<7;i++){
			final int finalI = i;
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"收集了"+ finalI +"课龙珠");
				try {
					cyclicBarrier.await(); // 等待，直到带到设置的计数器值
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	@Test
	public void testSemaphore() throws InterruptedException {
		// 线程数量；允许最多几个线程使用semaphore.acquire()
		Semaphore semaphore=new Semaphore(2);
		for(int i=1;i<=6;i++){
			new Thread(()->{
				try {
					semaphore.acquire();//获取
					System.out.println(Thread.currentThread().getName()+"获取了车位");
					TimeUnit.SECONDS.sleep(2);
					System.out.println(Thread.currentThread().getName()+"离开了车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();// 释放
				}

			},String.valueOf(i)).start();
		}
		TimeUnit.SECONDS.sleep(10);
	}

}
