package lambda;

import java.text.DecimalFormat;
import java.util.function.*;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月01日: 10:20
 */
class MyMoney {
    private final int moneny;
    public MyMoney(int moneny){
        this.moneny = moneny;
    }

//    public MyMoney() {
//
//    }

    public void printMoney(Function<Integer, String> moneyFormat){
        System.out.println("我的存款：" + moneyFormat.apply(this.moneny));
    }
    public static void printMoney2(int i){
        System.out.println("我的存款1：" + i);
        System.out.println("我的存款2:" + i);
        System.out.println("我的存款3：" + i);
    }
    public String getMoney(Function<Integer, String> moneyFormat){
        System.out.println("我的存款：" + moneyFormat.apply(this.moneny));
        return  moneyFormat.apply(this.moneny);
    }

    public Integer getMoney(Integer i){
        return i + 10000;
    }
}

public class MyMoneyDemo{
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(1999);
        Function<Integer, String> integerStringFunction = i -> new DecimalFormat("#,###").format(i);
//        myMoney.printMoney(integerStringFunction.andThen(s -> "人民币" + s));
//
//        // lambda函数匿名函数
//        Consumer<String> consumer = s -> System.out.println(s);
//        consumer.accept("hello");
//
//        // 方法引用
//        Consumer<String> consumer2 = System.out::println;
//        consumer2.accept("hello2");
//
//        Consumer<Integer> consumer3 = MyMoney::printMoney2;
//        consumer3.accept(1000);

//        Function<Function<Integer, String>, String> function = myMoney::getMoney;
//        function.apply(integerStringFunction);

//        Function<Integer, Integer> function1 = myMoney::getMoney;
//        UnaryOperator<Integer> operator = myMoney::getMoney;
//        System.out.println(function1.apply(10));
//        System.out.println(operator.apply(100));
//        BiFunction<MyMoney, Integer, Integer> biFunction = MyMoney::getMoney;
//        System.out.println(biFunction.apply(myMoney, 200));

        Function<Integer, MyMoney> supplier = MyMoney::new;
        System.out.println(supplier.apply(1));

    }
}
