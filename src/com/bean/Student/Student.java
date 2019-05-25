package com.bean.Student;

/**
 * @author Alexander Jiajiason
 * @date 2019-05-25 20:26
 */
public class Student {
    private int age;
    private String name;
    private String sex;

//    public Student(int age, String name, String sex) {
//        this.age = age;
//        this.name = name;
//        this.sex = sex;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
