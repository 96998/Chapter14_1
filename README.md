# 第14章应用Spring技术

###配置Bean
**一般配置内容都写在**
```aidl
<bean id="Student" class="com.bean.Student.Student"></bean>
```

### Application

```aidl
 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student stu = (Student)applicationContext.getBean("Student");
        stu.setSex("女");
        stu.setName("女朋友");
        stu.setAge(19);
        System.out.println(stu);
```

**ClassPathXmlApplicationContext()从当前类路径中检索配置文件并加载来创建容器**

## 依赖注入
**反正别人讲Spring框架降低耦合,什么Spring框架各个部分充分利用了依赖注入(Dependency Injection),这是什么咱也不知道,咱也不敢问**



## 依赖注入与控制反转

**依赖注入(Dependency Injection)和控制反转(Inversion of Control,IoC)是同一个概念,在传统的程序设计中,通常是由调用者来创建被调用者的实例,然而在Spring中,创建被调用者的工作由Spring容器来完成(控制反转),然后注入调用者(依赖注入),此用来削减程序耦合问题,这样使得对象与对象间的实现互相透明**



### 依赖注入由三种实现的模型,Spring框架支持后面两种

#### 1.接口注入

```
基于接口将调用与实现分离,
```

#### 2.设值注入(对应bean.xml)

```
通过JavaBean的属性(setter方法)可使服务对象指定服务
<bean id="Student" class="com.bean.Student.Student">
    <property name="age"><value>17</value></property>
    <property name="name"><value>YHJ</value></property>
    <property name="sex"><value>女</value></property>
</bean>
```

####  构造函数注入(对应Userbean.xml)

```
通过构造函数的参数为可服务对象指定服务
<bean id="Student" class="com.bean.Student.Student">
       <constructor-arg name="name"><value>CJJ</value></constructor-arg>
       <constructor-arg name="sex"><value>男</value></constructor-arg>
       <constructor-arg name="age"><value>20</value></constructor-arg>
</bean>
```



## Tips(构造函数注入与设值注入的冲突,原理暂时不清楚)

**值得注意的是构造函数注入与设值注入(Test.Test2())和在java中写代码(Test.Test1)有冲突,构造函数注入要在里面类里面设置构造函数(全部参数),而设值注入这些就只要get和set方法,有除了无参的构造函数就会出错,不过我不清楚原理**

