package xyz.dsvshx.map;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author dongzhonghua
 * Created on 2021-03-31
 */
public class TreeMapTest {
    public static void main(String[] args) {
        Map<Person, Integer> map = new TreeMap<>(Comparator.comparing(p -> p.name));
        map.put(new Person("C"), 3);
        map.put(new Person("A"), 1);
        map.put(new Person("B"), 2);
        for (Person key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println(map.get(new Person("B"))); // 2
    }
}

class Person {
    public String name;

    Person(String name) {
        this.name = name;
    }

    public String toString() {
        return "{Person: " + name + "}";
    }
}

