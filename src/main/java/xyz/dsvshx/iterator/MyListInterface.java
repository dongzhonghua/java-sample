package xyz.dsvshx.iterator;

import java.util.Iterator;

/**
 * @author dongzhonghua
 * Created on 2021-02-13
 */
public interface MyListInterface<T> {

    /**
     * 添加一个元素
     * @param obj 元素对象
     */
    void add(T obj);

    /**
     * 移除一个元素
     * @param obj 元素对象
     */
    void remove(T obj);

    /**
     * 获取容器的迭代器
     * @return 迭代器对象
     */
    Iterator<T> iterator();

    int size();
}
