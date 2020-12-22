package designmode.singleton;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

/**
 * @Author laijinhan
 * @date 2020/12/22 下午9:56
 */

enum SingletonEnum {

	INSTANCE;

	// 声明单例对象
	private SingletonEnum getInstance() {
		return INSTANCE;
	}
}

public class EnumSingleton {
	// 枚举类型是线程安全的，并且只会装载一次

	@Test
	public void test() throws Exception {
		SingletonEnum instance = SingletonEnum.INSTANCE;
		SingletonEnum instance1 = SingletonEnum.INSTANCE;
		System.out.println(instance);
		System.out.println(instance1);
		//	 尝试反射破解

		// 查看输出的class文件发现是无参数构造器, java.lang.NoSuchMethodException:
		// 不是期望的异常，用命令行javap反编译这个类发现还是无参数构造器
		// 用jad工具反编译，反现有两个参数string，int:java.lang.IllegalArgumentException: Cannot reflectively create enum objects
		// 得到想要的异常。
		Constructor<SingletonEnum> declaredConstructor = SingletonEnum.class.getDeclaredConstructor(String.class,int.class);
		declaredConstructor.setAccessible(true);
		SingletonEnum singletonEnum = declaredConstructor.newInstance();
		SingletonEnum singletonEnum1 = declaredConstructor.newInstance();
		System.out.println(singletonEnum);
		System.out.println(singletonEnum1);
	}
}