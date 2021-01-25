package xyz.dsvshx.threadLocal;

import java.util.HashMap;

/**
 * @author dongzhonghua
 * Created on 2021-01-24
 */
public class Client {
    private final ThreadLocal<String> myThreadLocal = ThreadLocal.withInitial(() -> "This is the initial value");

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        System.out.println(map.get("a"));
        System.out.println("test");
        System.out.println("test");
        System.out.println("test");
    }
}
