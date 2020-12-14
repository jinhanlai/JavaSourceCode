package designmode.proxy;

/**
 * @Author laijinhan
 * @date 2020/12/14 下午9:09
 */

/**
 * 抽象产品接口
 */
interface UserService {
	public void getName();
	public void getAddress();
}

/**
 * 具体产品实现类
 */
class UserServiceImpl implements UserService{
	@Override
	public void getName() {
		System.out.println("我是赖金寒");
	}

	@Override
	public void getAddress() {
		System.out.println("居住在成都郫县");
	}
}

public class StaticProxy implements UserService {
	private UserService target;

	public StaticProxy(UserServiceImpl target) {
		this.target=target;
	}


	@Override
	public void getName() {
		System.out.println("静态代理执行之前");
		target.getName();
		System.out.println("静态代理执行之后");

	}

	@Override
	public void getAddress() {
		System.out.println("静态代理执行之前");
		target.getAddress();
		System.out.println("静态代理执行之后");
	}

	public static void main(String[] args) {
		new StaticProxy(new UserServiceImpl()).getName();
		System.out.println("====");
		new StaticProxy(new UserServiceImpl()).getAddress();
	}
}
