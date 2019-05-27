package com.bean.ShowStudent;

import com.bean.Student.Student;

/**
 * @author Alexander Jiajiason
 * @date 2019-05-26 16:06
 * 用于完成根据Bean的名字装配
 */
public class ShowStudent {
    private Student student;

    @Override
    public String toString() {
        return "ShowStudent{" +
                "student=" + student +
                '}';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
