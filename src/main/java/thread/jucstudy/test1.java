package thread.jucstudy;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author laijinhan
 * @date 2020/12/18 下午10:44
 */


public class test1 {
	@Test
	public void testCoreNums(){
		// 获取Cpu核数
		System.out.println(Runtime.getRuntime().availableProcessors());
		new Thread().start();
	}

	@Test
	public void testLock(){
		Lock lock = new ReentrantLock();
		lock.lock();

		try {
			System.out.println("同步");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
