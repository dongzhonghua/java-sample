package xyz.dsvshx.function;

import java.util.function.Consumer;

public class Client {
    public static void main(String[] args) {
        MyFunction<String> function = System.out::println;
        function.call("param");
        Consumer<String> c = System.out::println;
        Consumer<String> stringConsumer = c.andThen(System.out::println);
        stringConsumer.accept("p");

    }
}
