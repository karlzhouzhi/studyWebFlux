package lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月01日: 16:17
 */
public class TypeDemo {

    public static void main(String[] args) {
//        IMath lambda = (x, y) -> x+y;
//        IMath[] lambdas = {(x, y) -> x+y};
//
//        Object o = (IMath)(x, y) -> x+y;
//
//        String str = "hello";
////        str = "world";
//        Consumer<String> consumer = s -> System.out.println(s + str);
//        consumer.accept("你好");

        // 柯里化，把多个参数的函数，转换成只有一个参数的函数
//        Function<Integer, Function<Integer, Integer>> fun = x ->y -> x+y;
//        System.out.println(fun.apply(200).apply(300));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function = x->y->z->x+y+z;
        System.out.println(function.apply(1).apply(2).apply(3));


    }
}

@FunctionalInterface
interface IMath{
    int add(int x, int y);
}
