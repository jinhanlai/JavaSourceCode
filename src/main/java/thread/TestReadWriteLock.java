package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author laijinhan
 * @date 2020/12/20 下午5:58
 */


public class TestReadWriteLock {
	@Test
	public void testReadwrite() {
		ReadWriteLock readWriteLock=new ReadWriteLock();

		for (int i = 0; i < 10; i++) {
			final int ii = i;
			new Thread(() -> {
				readWriteLock.put(String.valueOf(ii),ii);
			}, String.valueOf(i)).start();
		}
		for (int i = 0; i < 10; i++) {
			final int ii = i;
			new Thread(() -> {
				readWriteLock.get(String.valueOf(ii));
			}, String.valueOf(i)).start();
		}
	}
}

class ReadWriteLock {
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

	public void put(String key, Object value) {
		readWriteLock.writeLock().lock();
		System.out.println(Thread.currentThread().getName()+"写入"+key);
		map.put(key, value);
		System.out.println(Thread.currentThread().getName()+"写入ok");
		readWriteLock.writeLock().unlock();

	}
	public void get(String key) {
		readWriteLock.readLock().lock();
		System.out.println(Thread.currentThread().getName()+"读取"+key);
		map.get(key);
		readWriteLock.readLock().unlock();
		System.out.println(Thread.currentThread().getName()+"读取ok");
	}
}

