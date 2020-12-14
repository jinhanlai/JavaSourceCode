package designmode.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author laijinhan
 * @date 2020/12/14 下午9:52
 */

/**
 * CglibProxy通过创建子类来实现动态代理
 */
public class CglibProxy implements MethodInterceptor {
	private Object target;

	public CglibProxy(Object target) {
		this.target = target;
	}
	//创建一个代理对象
	public Object getProxyInstance(){
		//1.工具类
		Enhancer en = new Enhancer();
		//2.设置父类
		en.setSuperclass(target.getClass());
		//3.设置回调函数
		en.setCallback(this);
		//4.创建子类(代理对象)
		return en.create();
	}

	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("动态代理执行之前");
		//执行目标对象方法
		Object returnValue = method.invoke(target, args);
		System.out.println("动态代理执行之后");
		return returnValue;
	}

	public static void main(String[] args) {
		UserService proxyInstance = (UserService) new CglibProxy(new UserServiceImpl()).getProxyInstance();
		proxyInstance.getAddress();
	}
}
