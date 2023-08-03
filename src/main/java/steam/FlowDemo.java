package steam;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月03日: 16:15
 */
public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1 定义发布者，发布的数据类型是Integer
        // 直接调用jdk自带的SubmissionPublisher，它实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;


            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("onSubscribe");

                // 保存订阅关系，需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据
                this.subscription.request(1);

            }

            @Override
            public void onNext(Integer item) {
                System.out.println("onNext");

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 处理数据
                System.out.println("接收一个数据:" + item);

                // 处理完后，在请求一个数据
                this.subscription.request(1);

                // 或者数据接收完毕，告诉不再接收数据
//                this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部数据处理完毕或者发布者关闭了
                System.out.println("onComplete");
            }
        };

        //  3 发布者和订阅者建立订阅关系
        publisher.subscribe(subscriber);

        // 4 生产数据，并发布
        // 这里忽略数据生产过程
        int data = 100;
        // submit阻塞，订阅者缓存池数据满了，消费数据前，这个submit就被阻塞，消费者就可以调节生产者生产数据的速度
        for (int i = 0; i < 10000; i++) {
            System.out.println("生产者生产数据:" + i);
         publisher.submit(i);
        }

        // 5 结束后，关闭发布者
        // 正式环境应该finally或者try catch确保关闭
        publisher.close();

        Thread.currentThread().join(1000);
    }
}
