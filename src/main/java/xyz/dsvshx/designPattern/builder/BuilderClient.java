package xyz.dsvshx.designPattern.builder;

import xyz.dsvshx.designPattern.builder.User.UserBuilder;

/**
 * @author dongzhonghua
 * Created on 2021-01-27
 */
public class BuilderClient {
    public static void main(String[] args) {
        User yx = new UserBuilder()
                .setAge(1)
                .setName("yx")
                .build();
        System.out.println(yx.getName());
        System.out.println(yx.getAge());
        new Student.StudentBuilder().name("yx").build();
    }
}
