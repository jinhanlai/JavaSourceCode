package designmode.singleton;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author laijinhan
 * @date 2020/12/22 下午9:38
 */


public class InnerclassSingleton {
	private InnerclassSingleton() { }

	private static class InnerclassSingletonInstance {
		private static final InnerclassSingleton INSTANCE = new InnerclassSingleton();
	}

	public static InnerclassSingleton getInstance() {
		return InnerclassSingletonInstance.INSTANCE;
	}
	@Test
	public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		InnerclassSingleton instance = InnerclassSingleton.getInstance();
		Constructor<InnerclassSingleton> declaredConstructor = InnerclassSingleton.class.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);//  可以防伪私有变量
		InnerclassSingleton instance1 = declaredConstructor.newInstance();
		System.out.println(instance);
		System.out.println(instance1);
	}
}
