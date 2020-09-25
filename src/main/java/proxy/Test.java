package proxy;

/**
 * @Author laijinhan
 * @date 2020/9/25 11:37 下午
 */


public class Test {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(userService);
        UserService proxy = (UserService)proxyInvocationHandler.getProxy();
        proxy.getAddress();
        proxy.getName();
    }
}
