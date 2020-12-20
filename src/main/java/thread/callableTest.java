package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author laijinhan
 * @date 2020/12/20 下午4:18
 */

class MyThread implements Callable{

	@Override
	public Integer call() throws Exception {
		System.out.println("call()");
		return 1024;
	}
}

public class callableTest {
	@Test
	public void test() throws ExecutionException, InterruptedException {
		MyThread thread=new MyThread();
		FutureTask futureTask=new FutureTask(thread);
		new Thread(futureTask,"a").start();
		new Thread(futureTask,"b").start(); // 结果被缓存，不会打印
		// get()可能会阻塞，耗时较长
		System.out.println((Integer) futureTask.get());
	}
}
