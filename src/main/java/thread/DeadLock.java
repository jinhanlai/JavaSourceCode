package thread;

import java.awt.*;

/**
 * @Author laijinhan
 * @date 2020/12/17 下午10:49
 */

class Cat{

}
class Dog{

}

/**
 * 并发编程里面的模仿死锁
 */

/**
 * 死锁条件
 * * 互斥条件：一个资源每次只能被一个进程使用。
 * * 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源不释放。
 * * 不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺。
 * * 循环等待条件：若干进程之间形成一种头尾相连的循环等待资源关系。
 */
public class DeadLock implements Runnable {
	static Cat cat=new Cat();
	static Dog dog=new Dog();

	boolean flag;
	String name;
	DeadLock(boolean flag , String name){
		this.flag=flag;
		this.name=name;
	}


	@Override
	public void run() {
		if(flag){
			synchronized (cat){
				System.out.println(name+"正在用cat");
				synchronized (dog){
					System.out.println(name+"正在用dog");
				}
			}
		}else{
			synchronized (dog){
				System.out.println(name+"正在用dog");
				synchronized (cat){
					System.out.println(name+"正在用cat");
				}
			}
		}

	}
	public static void main(String[] args) {
		// 死锁
		// 互相等待，
		new Thread(new DeadLock(true,"小红")).start();
		new Thread(new DeadLock(false ,"小明")).start();
	}
}
