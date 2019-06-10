package c4_threadpool.v3_futurePattern;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package c4_threadpool.v3_futurePattern
 *
 * Future模式：对于多线程，如果线程A需要等待线程B的结果，那么线程A没必要一直等待线程B运行结束，可以先
 *              拿到一个未来的结果（FutureData），等到线程B有了结果再取真实的结果
 *
 * Future 模式的核心在于：去除了主函数的等待时间，并使得原本需要等待的时间段可以用于处理其他业务逻辑。
 */

interface Data{
    public abstract String getResult();
}

/**
  * 真正构造数据的方法，比较慢
  *
  * @author xuxin
  * @date   2019/3/12 17:59
  * @param
  * @return
  */
class RealData implements Data{

    protected final String result;

    public RealData(String para) {
        /*
            //模拟 realData业务处理需要很长时间
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
        }
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}

/**
  * Future数据，耗时短，但是是一个虚拟的数据，并且对 RealData 进行封装
  *
  * @author xuxin
  * @date   2019/3/12 17:59
  * @param
  * @return
  */
class FutureData implements Data{

    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData r){
        if (isReady){
            return;
        }
        this.realData = r;
        isReady = true;
        //realData已经注入，通知 getResult()
        notifyAll();
    }


    @Override
    public synchronized String getResult() {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}

/**
  * 对用户请求进行响应，由于RealData的处理时间较长，为了避免响应时间过长
  *     立即返回一个FutureData，并开启一个独立的线程来处理RealData
  *
  * @author xuxin
  * @date   2019/3/12 17:57
  * @param
  * @return
  */
class Client{
    public Data req(final String queryStr){
        final FutureData futureData = new FutureData();

        new Thread(()->{
            RealData r = new RealData(queryStr);
            futureData.setRealData(r);
        }).start();

        //立即返回futureData，由于realData处理很慢，所以让它在单独的线程中进行
        return futureData;
    }
}
/**
  * 模拟请求
  *
  * @author xuxin
  * @date   2019/3/12 17:56
  * @param
  * @return
  */
public class futureDemo {
    public static void main(String[] args) {
        Client c = new Client();

        //立即返回 futureData
        Data d = c.req("name");
        System.out.println("请求发送成功");

        System.out.println("继续执行Main线程其他任务");

        try {
            //模拟对其他业务逻辑的处理，在对其他业务逻辑的处理过程中，realData被创建，从而时间分配合理
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据结果为：" + d.getResult());

        System.out.println("啦啦啦");
    }
}
