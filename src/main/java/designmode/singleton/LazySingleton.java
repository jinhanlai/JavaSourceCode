package designmode.singleton;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author laijinhan
 * @date 2020/12/22 下午9:19
 */


public class LazySingleton {
	private LazySingleton(){
		System.out.println(Thread.currentThread().getName()+"ok");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static LazySingleton lazySingleton;

	public static LazySingleton getInstance(){
		if (lazySingleton == null) {
			lazySingleton = new LazySingleton();
		}
		return lazySingleton;
	}
	@Test
	public void test(){
		for(int i=0;i<10;i++){
			new Thread(()->{
				LazySingleton.getInstance();
			}).start();
		}
	}
}
