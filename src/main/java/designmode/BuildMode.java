package designmode; /**
 * @Author laijinhan
 * @date 2020/9/13 10:13 上午
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 抽象产品构成接口
 */
interface IBuilder{
    void buildPart1();
    void buildPart2();
    void buildPart3();
    Product build();
}
/**
 * 产品
 */
@Data
class Product{
    private String part1;
    private String part2;
    private String part3;

}
/**
 * 具体的创建者
 */
class TruckConcreateBuilder implements IBuilder{
    private Product product=new Product();

    @Override
    public void buildPart1() {
        product.setPart1("货车轮胎完成");
    }

    @Override
    public void buildPart2() {
        product.setPart2("货车发动机完成");
    }

    @Override
    public void buildPart3() {
        product.setPart3("货车车架完成");
    }

    @Override
    public Product build() {
        return product;
    }
}
class ConcreteBuilder implements IBuilder {
    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("轮胎完成");
    }

    @Override
    public void buildPart2() {
        product.setPart2("发动机完成");
    }

    @Override
    public void buildPart3() {
        product.setPart3("车架完成");
    }

    @Override
    public Product build() {
        return product;
    }
}

/**
 * 指挥者
 */
@AllArgsConstructor
@Data
class Conductor{
    private IBuilder builder;
    public Product construct() {
        builder.buildPart1();
        builder.buildPart2();
        builder.buildPart3();
        return builder.build();
    }
}
public class BuildMode {
    public static void main(String[] args) {
        IBuilder builder=new ConcreteBuilder();
        Conductor conductor = new Conductor(builder);
        Product product = conductor.construct();
        System.out.println("产品信息："+product.getPart1()+product.getPart2()+product.getPart3());

        conductor.setBuilder(new TruckConcreateBuilder());
        Product truck = conductor.construct();
        System.out.println("产品信息："+truck.getPart1()+truck.getPart2()+truck.getPart3());

    }
}
