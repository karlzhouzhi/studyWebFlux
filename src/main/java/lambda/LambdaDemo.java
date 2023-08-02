package lambda;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月01日: 09:19
 */
public class LambdaDemo {
    public static void main(String[] args) {
        Interface1 i1 = (i)-> i*2;
        // lambda表达式返回的是要实现的接口对象实例
        Interface1 i2 = i-> i*4;

        System.out.println(i1.add(1,2));
        System.out.println(i2.add(3,4));
    }
}

@FunctionalInterface
interface Interface1{
    int doubleNum(int i);
//    int doubleNum2(int i);
    default int add(int x, int y){
        System.out.println(this.doubleNum(1));
        return x+y;
    }
}
