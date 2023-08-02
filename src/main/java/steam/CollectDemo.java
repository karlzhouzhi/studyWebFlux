package steam;

import bean.Gender;
import bean.Grade;
import bean.Student;
import org.apache.commons.collections.MapUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月02日: 16:49
 */
public class CollectDemo {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(new Student("karl", 40, Gender.MAILE, Grade.ONE),
                new Student("grace", 23, Gender.FEMAIL, Grade.SIX),
                new Student("tom", 18, Gender.MAILE, Grade.FIVE),
                new Student("youyou", 22, Gender.FEMAIL, Grade.FOUR),
                new Student("旺旺", 22, Gender.FEMAIL, Grade.FOUR),
                new Student("super", 23, Gender.FEMAIL, Grade.FOUR),
                new Student("vivi", 41, Gender.MAILE, Grade.ONE),
                new Student("kk", 32, Gender.FEMAIL, Grade.TWO),
                new Student("oneone", 26, Gender.MAILE, Grade.FOUR),
                new Student("gougou", 22, Gender.MAILE, Grade.FIVE),
                new Student("ll", 29, Gender.FEMAIL, Grade.SIX));

//        List<Integer> ages = list.stream().map(Student::getAge).collect(Collectors.toList());
//        List<Integer> ages2 = list.stream().map(s->s.getAge()).collect(Collectors.toList());
//        System.out.println("所有学生的年龄:" + ages);

        // 统计汇总信息
//        IntSummaryStatistics intSummaryStatistics = list.stream().collect(Collectors.summarizingInt(Student::getAge));
//        System.out.println("统计汇总信息:" + intSummaryStatistics);

        // 分块
//        Map<Boolean, List<Student>> booleanListMap = list.stream().collect(Collectors.partitioningBy(s -> s.getGender() == Gender.FEMAIL));
//        MapUtils.verbosePrint(System.out, "男女生分块", booleanListMap);
        test();
    }

    public static void test(){
        Random random = new Random();
        Stream<Integer> stream = Stream.generate(()->random.nextInt())
                // 产生500个（无限流需要短路操作）
                .limit(500)
                // 第一个无状态操作
                .peek(s->System.out.print("peek:"+s))
                // 第二个无状态操作
                .filter(s->{
                    System.out.print("fliter:"+s);
                    return s>10;
                })
                // 第三个有状态操作
                .sorted((i,j)->{
                    System.out.print("排序:" + j + ", " + j);
                    return i.compareTo(j);
                })
                // 无状态
                .peek(s->System.out.print("peek:"+s));
        stream.count();
    }
}
