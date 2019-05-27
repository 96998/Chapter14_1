package com.bean.Student;

import com.bean.ShowStudent.ShowStudent;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @author Alexander Jiajiason
 * @date 2019-05-25 20:28
 */
public class Test {

    /**
     * BeanFactory方法已经过时
     */
    @org.junit.Test
    public void test(){
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        Student student = (Student) factory.getBean("Student");
        student.setName("方头");
        student.setAge(22);
        student.setSex("男");
        System.out.println(student);
    }

    /**
     * 如果包在目录底下,如com.xmu
     * 则路径改为com/xmu/applicationContext.xml
     * 有一种方式叫做接口注入,Spring不支持
     */
    @org.junit.Test
    public void  Test1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student stu = (Student)applicationContext.getBean("Student");
        stu.setSex("女");
        stu.setName("女朋友");
        stu.setAge(19);
        System.out.println(stu);
    }

    /**
     * 这是设值注入,值在src下的bean.xml中设置好了
     */
    @org.junit.Test
    public void Test2()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Student stu = (Student)applicationContext.getBean("Student");
        System.out.println(stu);
    }

    /**
     * 构造注入,这里要在Student.java中设置带全参的构造函数,但是这样子的话上面的那些Test()就会报错
     */
    @org.junit.Test
    public void Test3()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/bean/Student/Userbean.xml");
        Student stu = (Student)applicationContext.getBean("Student");
        System.out.println(stu);
    }

    /**
     * 自动装配Bean之根据Bean的名字装配
     */
    @org.junit.Test
    public void Test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ShowStudent showStudent = (ShowStudent)applicationContext.getBean("show1");
        System.out.println(showStudent);
    }
}
