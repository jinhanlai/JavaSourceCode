package designmode.singleton;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * @Author laijinhan
 * @date 2020/12/22 下午9:26
 */


public class LazySingletonLock {
	private LazySingletonLock(){
		synchronized (LazySingleton.class){
			if(lazySingleton!=null){
				throw new RuntimeException("不要用反射来破环单例");
			}
		}
		System.out.println(Thread.currentThread().getName()+"ok");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static LazySingletonLock lazySingleton;
	public static LazySingletonLock getInstance(){
		if (lazySingleton == null) {
			synchronized (LazySingleton.class){
				if(lazySingleton==null){
					lazySingleton = new LazySingletonLock();
				}
			}
		}
		return lazySingleton;
	}
	@Test
	public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Constructor<LazySingletonLock> declaredConstructor = LazySingletonLock.class.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);//  可以防伪私有变量
		LazySingletonLock instance1 = declaredConstructor.newInstance();
		LazySingletonLock instance = declaredConstructor.newInstance();
		System.out.println(instance);
		System.out.println(instance1);
	}
}
