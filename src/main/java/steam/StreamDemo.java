package steam;

import java.util.stream.IntStream;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月02日: 09:56
 */
public class StreamDemo {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int sum = IntStream.of(nums).map(i->i*2).sum();
        System.out.println(sum);
    }
}
