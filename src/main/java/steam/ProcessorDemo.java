package steam;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月03日: 16:36
 */
public class ProcessorDemo {
    public static void main(String[] args) {
        // 1 直接使用jdk自带的SubmissionPublisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2 定义处理器，对数据进行过滤，并转换成string
        MyProcessor processor = new MyProcessor();

        // 3 发布者和处理器建立订阅关系
        publisher.subscribe(processor);

        // 4 定义最终订阅者，消费string类型数据
        Flow.Subscriber<String> subscriber = new Flow.Subscriber() {
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
            public void onNext(Object item) {
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

        // 5 处理器和订阅者建立订阅关系
        processor.subscribe(subscriber);

        // 6 生成数据和发布
        publisher.submit(100);
        publisher.submit(-100);

        // 7 结束后，关闭发布者
        publisher.close();

        try {
            Thread.currentThread().join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * @Description: 对数据过滤，加工
 * @param: null
 * @return * @return null
 * @author zhouzhi96
 * @date 2023/8/3 16:41
 */
class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("processor onsubscribe");
        // 保存订阅关系，需要用它来给发布者响应
        this.subscription= subscription;

        // 请求一个数据
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("processor处理一个数据:" + item);

        if(item>0){
            this.submit("processor转换后的数据:" + item);
        }

        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("procesor 出错");
    }

    @Override
    public void onComplete() {
        System.out.println("procesor 处理完毕");
    }
}