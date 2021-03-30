package xyz.dsvshx.compare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dongzhonghua
 * Created on 2021-03-30
 */
public class CompareTest {
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("a", 18));
        employeeList.add(new Employee("b", 15));
        employeeList.add(new Employee("c", 20));

        // 匿名内部类
        employeeList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getAge().compareTo(e2.getAge());
            }
        });
        // lambda表达式
        employeeList.sort((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        // 静态方法comparing
        employeeList.sort(Comparator.comparing(Employee::getAge));
        // 倒序
        employeeList.sort(Comparator.comparing(Employee::getAge).reversed());
        // thenComparing
        employeeList.sort(Comparator.comparing(Employee::getAge).reversed().thenComparing(Employee::getName));
    }

    @Data
    @AllArgsConstructor
    static class Employee {

        private String name;

        private Integer age;
    }
}
