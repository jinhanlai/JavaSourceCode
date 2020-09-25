package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author laijinhan
 * @date 2020/9/25 11:34 下午
 */


public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public void setTarget(Object target){
        this.target=target;
    }
    public  Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        return result;
    }
}
