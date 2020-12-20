package thread.jucstudy;

/**
 * @Author laijinhan
 * @date 2020/12/19 上午11:40
 */


import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发编程里面的生产者消费者问题
 */

// 用synchronized来实现
class Resource {
	private  int number=0;
	public synchronized void increment() throws InterruptedException {
		/**
		 * wait只能写在while里面，否则用if的话会出现虚假唤醒的问题；
		 */
		while (number!=0){
			this.wait();
		}
		number++;
		System.out.println(Thread.currentThread().getName()+"-->"+number);
		this.notifyAll();
	}
	public synchronized  void  decrement() throws InterruptedException {
		while (number==0){
			this.wait();
		}
		number--;
		System.out.println(Thread.currentThread().getName()+"-->"+number);
		this.notifyAll();
	}

}
// condition 代替锁的监视器，Lock 来替换synchronized
// condition可以精准的唤醒和通知线程
class Resource1{
	private  int number=0;
	Lock locks=new ReentrantLock();
	Condition con1=locks.newCondition();
	Condition con2=locks.newCondition();

	public void increment(){
		locks.lock();
		try {
			while (number!=0){
				con1.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName()+"-->"+number);
			con2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locks.unlock();
		}
	}
	public void decrement(){
		locks.lock();
		try {
			while (number==0){
				con2.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName()+"-->"+number);
			con1.signal();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locks.unlock();
		}
	}

}
public class ProduceCustom11 {
	@Test
	public void test() {
		Resource1 datas=new Resource1();
		new Thread(()->{
			for(int i=0;i<10;i++) {
				datas.increment();
			}
		},"a").start();;
		new Thread(()->{
			for(int i=0;i<10;i++) {
				datas.decrement();
			}
		},"b").start();;
	}
}