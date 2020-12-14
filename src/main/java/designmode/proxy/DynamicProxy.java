package designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author laijinhan
 * @date 2020/12/14 下午9:20
 */


public class DynamicProxy {
	private Object target; //被代理的任意接口
	public DynamicProxy(Object target){
		this.target=target;
	}

	public Object getProxyInstance() { //生成代理类
		return Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				(proxy, method, args) -> {
					System.out.println("动态代理执行之前");
					//执行目标对象方法
					Object returnValue = method.invoke(target, args);
					System.out.println("动态代理执行之后");
					return returnValue;
				}
		);
	}

	public static void main(String[] args) {
		// 具体的产品类
		UserServiceImpl userService = new UserServiceImpl();
		// 动态代理类,获取代理对象
		UserService proxyInstance = (UserService)new DynamicProxy(userService).getProxyInstance();
		// 获取代理对象
		proxyInstance.getAddress();

	}
}

 class DynamicProxy0 implements InvocationHandler {
	private Object target; //被代理的任意接口
	public DynamicProxy0(Object target){
		this.target=target;
	}

	public Object getProxyInstance() { //生成代理类
		return Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				this
		);
	}

	 @Override
	 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		 System.out.println("动态代理执行之前");
		 //执行目标对象方法
		 Object returnValue = method.invoke(target, args);
		 System.out.println("动态代理执行之后");
		 return returnValue;
	 }
	 public static void main(String[] args) {
		 // 具体的产品类
		 UserServiceImpl userService = new UserServiceImpl();
		 // 动态代理类,获取代理对象
		 UserService proxyInstance = (UserService)new DynamicProxy0(userService).getProxyInstance();
		 // 获取代理对象
		 proxyInstance.getName();

	 }

 }