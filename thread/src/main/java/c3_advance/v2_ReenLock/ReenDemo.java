package c3_advance.v2_ReenLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuxin
 * @version v1.0
 * @project thread
 * @package c3_advance.v2_ReenLock
 *
 * ReenLock的标准用法
 *        Lock lock = ...;
 *
 *        lock.lock
 *        try {
 *             // manipulate protected state
 *        } finally {
 *             lock.unlock();
 *        }
 *
 * 一些其他方法：
 *      tryLock() :不断的尝试获取锁，获取到锁则返回 true，否则false
 *                      值得注意的是 哪怕初使化的ReenLock是公平锁，也会让执行了 tryLock()方法的线程优先执行
 *
 *      tryLock(tryLock(long timeout, TimeUnit unit)): 在timeout时间内尝试获取锁获，取到锁则返回 true，否则false
 *
 * todo ReentrantLock 原理 http://www.importnew.com/24006.html
 */
public class ReenDemo {
    public static void main(String[] args) {

        //todo ReenLock的实现原理 https://www.cnblogs.com/xrq730/p/4979021.html  https://juejin.im/entry/5ae02a7c6fb9a07ac76e7b70
        //初始化 非公平的可重入锁
        final Lock lock = new ReentrantLock();
        /*
            公平的可重入锁的声明方式
            Lock lock = new ReentrantLock(true)
        */

        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取锁，只有在获取到锁后才会执行 lock.lock()下面的代码
                lock.lock();
                try {
                    int i = 1;
                    while (true){
                        System.out.println("我是A ：" + i++);
                    }
                }finally {
                    /*
                     unlock() : 释放锁资源
                     为什么放在 finally块中？
                     确保 当 try块 中代码出现异常时，锁资源一定会被释放
                    */
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock();
                try {
                    System.out.println("我是B");
                }finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
