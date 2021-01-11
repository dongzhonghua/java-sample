package xyz.dsvshx.function;

// 看到函数式接口第一反应是什么？
// 函数式接口引用的是一个函数。
// 他是一个函数，函数就涉及到调用和传参。调用这个函数就是用接口中的抽象方法，传参就是抽象函数中的参数，
@FunctionalInterface
public interface MyFunction<T> {
    void call(T t);
}
