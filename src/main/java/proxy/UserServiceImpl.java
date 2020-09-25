package proxy;

/**
 * @Author laijinhan
 * @date 2020/9/25 11:33 下午
 */


public class UserServiceImpl implements UserService{
    @Override
    public void getName() {
        System.out.println("我是赖金寒");
    }

    @Override
    public void getAddress() {
        System.out.println("居住在成都郫县");
    }
}
