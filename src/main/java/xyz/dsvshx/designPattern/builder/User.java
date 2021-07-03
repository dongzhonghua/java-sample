package xyz.dsvshx.designPattern.builder;

/**
 * @author dongzhonghua
 * Created on 2021-01-27
 */
public class User {
    private String name;
    private Integer age;

    public static class UserBuilder {
        private String name;
        private int age;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(UserBuilder b) {
        this.age = b.age;
        this.name = b.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
