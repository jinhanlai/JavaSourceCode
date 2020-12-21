package thread.jucstudy;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author laijinhan
 * @date 2020/12/21 下午10:27
 */


public class TestVolatile {
	// 不加volatile就会死循环
	private volatile static int number=0;
	@Test
	public void testVisible() throws InterruptedException {
		new Thread(()->{
			while (number==0){

			}
		}).start();

		TimeUnit.SECONDS.sleep(2);
		number=1;
		System.out.println(1);
	}

	// private volatile static int count=0;
	// public  void add(){
	// 	count++;
	// }
	private volatile static AtomicInteger count=new AtomicInteger();
	public  void add(){
		count.getAndIncrement();
	}
	@Test
	public void testmic(){
		for(int i=0;i<20;i++){
			new Thread(()->{
				for(int j=0;j<1000;j++){
					add();
				}
			}).start();
		}
		while (Thread.activeCount()>2){// main gc
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName()+count);
	}
}
