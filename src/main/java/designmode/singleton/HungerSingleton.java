package designmode.singleton;

import org.junit.jupiter.api.Test;

/**
 * @Author laijinhan
 * @date 2020/12/22 下午9:11
 */


public class HungerSingleton {
	private HungerSingleton(){

	}
	private static final HungerSingleton singleton=new HungerSingleton();

	public static HungerSingleton getInstance() {
		return singleton;
	}
	@Test
	public void test(){
		HungerSingleton hungerSingleton= HungerSingleton.getInstance();
		HungerSingleton hungerSingleton1= HungerSingleton.getInstance();
		System.out.println(hungerSingleton.equals(hungerSingleton1));// true
	}
}
