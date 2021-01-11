package xyz.dsvshx.function;

import java.util.function.ToIntBiFunction;

public class SupplierDemo {
    public static void main(String[] args) {
        ToIntBiFunction<Integer, Integer> toIntBiFunction = Integer::sum;
        int i = toIntBiFunction.applyAsInt(1, 2);
        System.out.println(i);
    }
}
