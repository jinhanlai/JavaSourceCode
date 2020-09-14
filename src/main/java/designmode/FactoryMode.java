package designmode; /**
 * @Author laijinhan
 * @date 2020/9/12 12:27 下午
 */


/**
 * 抽象产品类
 */
interface IPizza{
    String getPizzaName();
    Double getPrice();
}

/**
 * 具体的产品类
 */
class BjCheesePizza implements IPizza{

    @Override
    public String getPizzaName() {
        return "北京芝士披萨";
    }

    @Override
    public Double getPrice() {
        return 999.99;
    }
}
/**
 * 具体的产品类
 */
class ShCheesePizza implements IPizza{
    @Override
    public String getPizzaName() {
        return "上海芝士披萨";
    }

    @Override
    public Double getPrice() {
        return 888.88;
    }
}

/**
 * 抽象工厂
 */
interface ICreateFactory{
    IPizza getICreatePizza();
}

/**
 * 具体工厂
 */
class BjPizzaFactory implements ICreateFactory{

    @Override
    public IPizza getICreatePizza() {
        return new BjCheesePizza();
    }
}
class ShPizzaFactory implements ICreateFactory {
    @Override
    public IPizza getICreatePizza() {
        return new ShCheesePizza();
    }
}

public class FactoryMode {

    public static void main (String[] args){
        ICreateFactory bjPizzaFactory = new BjPizzaFactory();
        ICreateFactory shPizzaFactory = new ShPizzaFactory();

        IPizza iCreatePizza = bjPizzaFactory.getICreatePizza();
        IPizza iCreatePizza1 = shPizzaFactory.getICreatePizza();
        System.out.println("你选择啦"+iCreatePizza.getPizzaName()+"价格为："+iCreatePizza.getPrice());
        System.out.println("你选择啦"+iCreatePizza1.getPizzaName()+"价格为："+iCreatePizza1.getPrice());
    }

}