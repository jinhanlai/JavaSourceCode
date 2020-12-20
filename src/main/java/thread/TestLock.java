package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author laijinhan
 * @date 2020/12/20 下午8:09
 */


public class TestLock {
	@Test
	public void main() throws InterruptedException {
		// 调用的两个方法是同一把锁
		// LockThread lockThread=new LockThread();

		// 下面这是两个不同的锁
		LockThread lockThread=new LockThread();
		LockThread lockThread1=new LockThread();

		// 下面这个两个对象使用的是同一把锁，因为用static修饰类，
		// LockThread1 lockThread=new LockThread1();
		// LockThread1 lockThread1=new LockThread1();
		new Thread(()->{
			try {
				lockThread.hello();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"a").start();
		new Thread(()->{
			lockThread1.hai();
		},"b").start();
		TimeUnit.SECONDS.sleep(10);
	}
}
class  LockThread{
	/**
	 *
	 * 如果是该类的同一个实例，则这两个方法是同一个锁。
	 */
	public synchronized void  hello() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("hello");
	}
	public synchronized void  hai(){
		System.out.println("hai");
	}
}
class  LockThread1{
	/**
	 * 类一加载就完成初始化，无论创建多少个实例都是同一把锁
	 */
	public static synchronized void  hello() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("hello");
	}
	public static synchronized void  hai(){
		System.out.println("hai");
	}
}
