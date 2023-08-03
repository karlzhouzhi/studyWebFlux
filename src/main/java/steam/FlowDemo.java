package steam;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

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
        publisher.submit(data);
        publisher.submit(1);
        publisher.submit(2);
        publisher.submit(3);

        // 5 结束后，关闭发布者
        // 正式环境应该finally或者try catch确保关闭
        publisher.close();

        Thread.currentThread().join(1000);
    }
}
