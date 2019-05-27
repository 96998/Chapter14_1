# 第14章应用Spring技术

### 配置Bean
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



## 自动装配Bean
```aidl
<bean id="show1" class="com.bean.ShowStudent.ShowStudent" autowire="byName"></bean>  <!--autowire用处?-->
    <bean class="com.bean.Student.Student" id="student">
        <property name="name"><value>Alexander</value></property>
        <property name="sex"><value>男</value></property>
        <property name="age"><value>20</value></property>
    </bean>
```
```aidl
当我们要往一个bean的某个属性里注入另外一个bean，我们会使用<property> + <ref/>标签的形式。但是对于大型项目，假设有一个bean A被多个bean引用注入，如果A的id因为某种原因修改了，那么所有引用了A的bean的<ref/>标签内容都得修改，这时候如果使用autowire="byType"，那么引用了A的bean就完全不用修改了。autowire到底都什么用？我们继续往下看。


autowire的用法：

<bean id="auto" class="example.autoBean" autowire="byType"/>

 
autowire属性有5种模式，如下表：

模式

说明

no

(默认)不采用autowire机制.。这种情况，当我们需要使用依赖注入，只能用<ref/>标签。

byName

通过属性的名称自动装配（注入）。Spring会在容器中查找名称与bean属性名称一致的bean，并自动注入到bean属性中。当然bean的属性需要有setter方法。例如：bean A有个属性master，master的setter方法就是setMaster，A设置了autowire="byName"，那么Spring就会在容器中查找名为master的bean通过setMaster方法注入到A中。

byType

通过类型自动装配（注入）。Spring会在容器中查找类（Class）与bean属性类一致的bean，并自动注入到bean属性中，如果容器中包含多个这个类型的bean，Spring将抛出异常。如果没有找到这个类型的bean，那么注入动作将不会执行。

constructor

类似于byType，但是是通过构造函数的参数类型来匹配。假设bean A有构造函数A(B b,   C c)，那么Spring会在容器中查找类型为B和C的bean通过构造函数A(B b, C c)注入到A中。与byType一样，如果存在多个bean类型为B或者C，则会抛出异常。但时与byType不同的是，如果在容器中找不到匹配的类的bean，将抛出异常，因为Spring无法调用构造函数实例化这个bean。

default

采用父级标签（即beans的default-autowire属性）的配置。

其中byType和constructor模式也支持数组和强类型集合（即指定了集合元素类型）。如bean A有个属性定义是List<Foo>类型，Spring会在容器中查找所有类型为Foo的bean，注入到该属性。记住是Foo，不是List。

另外如果集合和Map集合，那么Map的key必须是String类型，Spring会根据value的类型去匹配。例如有属性bean A中有一个属性为Map<String, Foo> p，容器中有bean B和C类型均为Foo，那么A实例化完成后，p属性的值为：{"B"：B的实例对象，"C"：C的实例对象}。


除了可以给单独一个bean配置autowire属性，我们还可以给某个bean配置文件的<beans/>标签添加default-autowire属性。在<beans/>标签中指定default-autowire属性，那么对于子标签<bean/>如果没有单独的设置autowire属性，那么将采用父标签<beans/>的default-autowire属性的模式，如果单独设置了autowire属性，则采用自己的模式。

虽然autowrie给我们带来配置的便利性，但是也有缺点，比如会导致bean的关系没那么显而易见，所以用autowire还是ref还是需要根据项目来决定。

autowire-candidate

前面我们说到配置有autowire属性的bean，Spring在实例化这个bean的时候会在容器中查找匹配的bean对autowire bean进行属性注入，这些被查找的bean我们称为候选bean。作为候选bean，我凭什么就要被你用，老子不给你用。所以候选bean给自己增加了autowire-candidate="false"属性（默认是true），那么容器就不会把这个bean当做候选bean了，即这个bean不会被当做自动装配对象。同样，<beans/>标签可以定义default-autowire-candidate="false"属性让它包含的所有bean都不做为候选bean。我的地盘我做主。
```

## Spring AOP(Aspect Oriented Programming,面向切面编程)
