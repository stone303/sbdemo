package com.example.sbdemo.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/****
 *
 * 在Java中，当你运行一个类时，底层执行过程包括以下步骤：
 * 类加载：Java虚拟机（JVM）通过类加载器加载类的字节码文件（.class文件），并将其转换成运行时的数据结构。类加载包括加载、链接和初始化阶段。
 * 链接阶段：
 * 验证：验证字节码的合法性，确保它符合Java虚拟机规范。
 * 准备：为类的静态变量分配内存，并设置默认初始值。
 * 解析：将符号引用转换为直接引用，以便在运行时可以准确地访问到所需的方法和字段。
 * 初始化阶段：执行类的静态变量初始值设定和静态代码块，进行类的初始化工作。在初始化阶段，可以在静态代码块中执行一些额外的逻辑或初始化操作。
 * 创建对象：通过调用类的构造方法来创建对象。对象的创建一般发生在类的实例化过程中，可以使用关键字new实例化对象。
 * 调用方法：一旦对象创建完成，你可以通过对象调用类中的实例方法。此时，会执行方法中的代码逻辑。
 * 垃圾回收：当对象不再被引用或程序结束时，Java垃圾回收机制会回收不再使用的内存空间。
 * 需要注意的是，以上是Java类在执行过程中的常见步骤，具体的执行细节可能会因为Java虚拟机的实现和编译器优化等因素而有所不同。
 * 总结起来，Java类的底层执行过程包括类加载（JVM）通过类加载器加载类的字节码文件（.class文件）、链接阶段（验证、准备和解析）、初始化阶段、
 * 对象创建、方法调用和垃圾回收。这一系列步骤确保了Java类的正确加载、处理和执行。
 *
 */

public class DataPoolTest {
    @Autowired
    DataPool dataPool;
    @Autowired
    ProDataPool proDataPool;
    @Test(invocationCount = 3,threadPoolSize = 1)
    public void test1()
    {
        //dataPool.addData("appkey", "3905d343fe72156d");
        //System.out.println(dataPool.getData("appkey"));
        proDataPool.addProperty("appkeyt55", "55555555555555");
        proDataPool.addProperty("appkeyt66", "666");
        proDataPool.addProperty("appkeyt99", "999");
        System.out.println(proDataPool.getProperty("appkey55"));
    }

    @Test(invocationCount = 4,threadPoolSize = 1)
    public void test2()
    {
        System.out.println(proDataPool.getProperty("appkeyt99"));
        System.out.println(proDataPool.getProperty("appkeyt66"));
        System.out.println(proDataPool.getProperty("appkeyt55"));
    }

    @Test
    public void test3()
    {
        proDataPool.clearProperty("appkey99");
        proDataPool.clearProperty("appkey66");
        proDataPool.clearProperty("appkeyt55");
       proDataPool.clearProperty("appkeyt99");
       proDataPool.clearProperty("appkeyt66");
       proDataPool.clearProperty("appkeyt66");
    }
}