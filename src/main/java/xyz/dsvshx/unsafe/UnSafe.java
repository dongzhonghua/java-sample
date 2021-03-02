package xyz.dsvshx.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @author dongzhonghua
 * Created on 2021-03-02
 */
public class UnSafe {
    public static Unsafe unsafe;

    static {
        try {
            // 应用开发中使用unsafe对象必须通过反射获取
            Class<?> clazz = UnSafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(clazz);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }
}
