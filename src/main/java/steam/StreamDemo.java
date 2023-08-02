package steam;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月02日: 09:56
 */
public class StreamDemo {
    public static int doubleNum(int i){
        System.out.println("执行乘以2");
        return i*2;
    }

    public static void getStream(){
        List<String> list = new ArrayList<>();

        // 集合产生流
        list.stream();
        list.parallelStream();

        // 数组产生流
        Arrays.stream(new int[]{11,12,13});

        // int
        IntStream.of(1,2 ,3);
        IntStream.rangeClosed(1,10);

        // 自己产生流
        new Random().ints().limit(10);
        Stream.generate(()->new Random().nextInt()).limit(20);
    }

//    public static void testMiddleStream(){
//        // Stream流编程 - 中间操作
//        // map; flatMap
//        // filter
//        // peek
//        String str = "hello web flux, i am coming";
////        Stream.of(str.split(" ")).filter(s->s.length()>2).map(s -> s.length()).forEach(len -> System.out.println(len));
//////        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(System.out::println);
////        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> {
////            System.out.println((char)i.intValue());
////        });
////        Stream.of(str.split(" ")).peek(System.out::println).forEach(s->System.out.println(s));
//        new Random().ints().filter(i->i>0&&i<100).limit(10).forEach(System.out::println);
//        // distinct, sorted, limit/skip
//    }

    /** 终止操作；
     * 短路操作:findFirst, findAny
     * 非短路操作：forEach/forEachOrder; collect; reduce
     * max; min; count
     */
    public static void testOperation(){
        String str = "hello web flux, i am coming go";
//        str.chars().parallel().forEachOrdered(i->System.out.println((char)i));
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
//        list.stream().forEach(s->System.out.println(s));
//        list.stream().forEach(System.out::println);

//        Optional<String> reduce = list.stream().reduce((s1, s2) -> s1 + "|" + s2);
//        System.out.println(reduce.orElse(""));

//        String reduce = list.stream().reduce("", (s1, s2) -> s1 + "|" + s2);
//        System.out.println(reduce);
//
//        Integer length = Stream.of(str.split(" ")).map(s->s.length()).reduce(0, (s1,s2)->s1+s2);
//        System.out.println(length);

//        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2)->s1.length()-s2.length());
//        System.out.println(max.get());

        OptionalInt o = new Random().ints().findFirst();
        System.out.println(o.getAsInt());
    }

    public static void testParallelStream(){
//        IntStream.range(1, 100).peek(StreamDemo::debug).count();
        // 修改默认线程池的线程数
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
//        IntStream.range(1, 100).parallel().peek(StreamDemo::debug).count();

        // 自己的线程池运行自己的并行任务
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel().peek(StreamDemo::debug).count());
        pool.shutdown();

        synchronized (pool){
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void debug(int i){
        System.out.println(Thread.currentThread().getName() + ", debug " + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testParallelStream();
//        testOperation();
//        int[] nums = {1,2,3,4,5};
//        // map就是中间操作，返回stream的操作
//        // sum就是终止操作
////        int sum = IntStream.of(nums).map(i->i*2).sum();
//        int sum = IntStream.of(nums).map(StreamDemo::doubleNum).sum();
//        System.out.println(sum);
//        testMiddleStream();
    }
}
