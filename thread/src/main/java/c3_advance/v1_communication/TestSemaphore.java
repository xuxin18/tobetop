package c3_advance.v1_communication;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *	Semaphore（信号量）：用来控制同时访问特定资源的线程数量
 *          很难理解Semaphore所表达的含义，
 *              只能把它比作是控制流量的红绿灯，比如XX马路要限制流量，只允许同时有一百辆车在这条路上行使，其他的都必须在路口等待，
 *              所以前一百辆车会看到绿灯，可以开进这条马路，后面的车会看到红灯，不能驶入XX马路，但是如果前一百辆中有五辆车已经离
 *              开了XX马路，那么后面就允许有5辆车驶入马路，这个例子里说的车就是线程，驶入马路就表示线程在执行，离开马路就表示线程
 *              执行完成，看见红灯就表示线程被阻塞，不能执行。
 *
 * Semaphore应用场景：
 *        用于做流量控制（特别是公有资源有限的应用场景。例如：数据库连接）
 *
 * 除了例子中的方法还有：
 * 		int getQueueLength()：返回正在等待获取许可证的线程数。
 * 		boolean hasQueuedThreads() ：是否有线程正在等待获取许可证。
 * 		void reducePermits(int reduction) ：减少reduction个许可证。是个protected方法。
 *		Collection getQueuedThreads() ：返回所有等待获取许可证的线程集合。是个protected方法。
 *
 * 静态导入(import static)：
 * 	  静态导入某个方法后可以直接用方法名调用静态方法，而不必用ClassName.方法名 的方式来调用。
 *       没有用静态导入的写法
 *          ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
 *       使用了静态导入后的写法：
 *          ExecutorService threadPool = newFixedThreadPool(THREAD_COUNT);
 */
public class TestSemaphore {

	public static void main(String[] args) {
		//3 为可用的许可证数量，即能同时访问某资源的线程数为 3,
		Semaphore semaphore = new Semaphore(3);
		for (int i = 1; i <= 10; i++) {
			ThradDemo001 thradDemo001 = new ThradDemo001("第" + i + "个线程", semaphore);
			thradDemo001.start();
		}
	}

	static class ThradDemo001 extends Thread {
		private String name;
		private Semaphore s;

		public ThradDemo001(String name, Semaphore s) {
			this.name = name;
			this.s = s;
		}

		@Override
		public void run() {
			// availablePermits() 返回可用的许可证数量
			int availablePermits = s.availablePermits();
			if (availablePermits > 0) {
				System.out.println(name + "有许可证了.....");
			} else {
				System.out.println(name + "怎么没有许可证了...");
			}

			try {
				// acquire()申请许可证，只有获得到许可证后，才能执行 下面的业务逻辑
				s.acquire();

				System.out.println(name + "执行业务代码" + ",剩下许可证数目为：:" + s.availablePermits());
				//模拟线程执行所需要的时间
				Thread.sleep(new Random().nextInt(1000));
				System.out.println(name + "业务执行完毕，释放许可证!");

				//让执行完毕的线程归还许可证,释放资源
				s.release();

				//释放后其他线程获取许可证遵循 FIFO 原则，即先被阻拦的先获取许可证

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

} 