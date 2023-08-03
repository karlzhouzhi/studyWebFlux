Predicate<T>        T, boolean, 断言
Consumer<T>         T, /,       消费一个数据
Function<T,R>       T, R,       输入T，输出R
Supplier<T>         /, T,       提供一个数据
UnaryOperator<T>    T, T,       一元函数（输入输出类型相同）
BiFunction<T,U,R>  (T,U), R,    两个输入的函数
BinaryOperator<T>   (T,T), T,   二元函数（输入输出类型相同）

为什么要使用异步servlet? 同步servlet阻塞了什么？
tomcat容器的servlet线程被阻塞
异步servlet是怎么样工作的？
不会阻塞tomcat的servlet线程，立即释放，可以去处理别的请求，从而达到高并发