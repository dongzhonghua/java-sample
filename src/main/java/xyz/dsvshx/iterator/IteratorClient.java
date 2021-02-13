package xyz.dsvshx.iterator;

import java.util.Iterator;

/**
 * @author dongzhonghua
 * Created on 2021-02-13
 */
public class IteratorClient {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size());
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
