Predicate<T>        T, boolean, 断言
Consumer<T>         T, /,       消费一个数据
Function<T,R>       T, R,       输入T，输出R
Supplier<T>         /, T,       提供一个数据
UnaryOperator<T>    T, T,       一元函数（输入输出类型相同）
BiFunction<T,U,R>  (T,U), R,    两个输入的函数
BinaryOperator<T>   (T,T), T,   二元函数（输入输出类型相同）