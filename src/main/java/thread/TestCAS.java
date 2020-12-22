package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author laijinhan
 * @date 2020/12/22 下午10:25
 */


public class TestCAS {
	@Test
	public void test(){
		AtomicInteger atomicInteger=new AtomicInteger(200);

		// B线程，捣乱 200--》2001--》200
		atomicInteger.compareAndSet(200,2001);
		atomicInteger.compareAndSet(2001,200);
		System.out.println(atomicInteger.get());

		// 正常的A线程拿到的200，不是之前的200了
		atomicInteger.compareAndSet(200,2002);
		System.out.println(atomicInteger.get());
	}
	@Test
	public void testABA() throws InterruptedException {
		AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(10,1);
		// a线程负责修改值 10--》11--》10
		new Thread(()->{
			int stamp = atomicStampedReference.getStamp();
			System.out.println("a1=>"+stamp);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(atomicStampedReference.compareAndSet(10, 11,
					stamp, stamp + 1));
			System.out.println("a2=>"+atomicStampedReference.getStamp());

			System.out.println(atomicStampedReference.compareAndSet(11, 10,
					atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
			System.out.println("a2=>"+atomicStampedReference.getStamp());

		},"a").start();

		// b线程
		new Thread(()->{
			int stamp = atomicStampedReference.getStamp();
			System.out.println("b1=>"+stamp);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(atomicStampedReference.compareAndSet(10, 66,
					stamp, stamp + 1));
			System.out.println("b2=>"+atomicStampedReference.getStamp());

		},"b").start();

		TimeUnit.SECONDS.sleep(3);
	}
}
