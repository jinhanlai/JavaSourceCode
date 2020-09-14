package designmode; /**
 * @Author laijinhan
 * @date 2020/9/13 10:49 上午
 */

import lombok.AllArgsConstructor;

/**
 * 被适配的类
 */
class Adaptee{
    public int outPut220V() {
        System.out.println("输出220V的电压");
        return 220;
    }
}

/**
 * 具体的抽象接口
 */
 interface Target {

    int outPut22V();
}

/**
 * 适配器类
 */
@AllArgsConstructor
 class Adapter implements Target{
    private Adaptee adaptee;
    @Override
    public int outPut22V() {
        int i = adaptee.outPut220V();
        // 2.适配转换操作
        int result = i / 10;
        // 返回适配后结果
        return result;
    }

}
/**
 * 客户端类
 */
class Phone{
    public void charging(Target target) {
        // 得到期望结果，开始后续操作
        int i = target.outPut22V();
        System.out.println("当前电压为"+i+"开始充电操作");
    }
}
public class AdapterMode{
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new Adapter(new Adaptee()));
    }
}

