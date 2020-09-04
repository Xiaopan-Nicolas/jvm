package jvm.classload;

/**
 * @Author: cunxiaopan
 * @Date: 2020/09/04 10:01
 * @Description: 引导类加载器 使用C/C++ 编写，不能获取对象，主要加载java核心类库以及加载其他的类加载器本身； 类加载器之间没有继承关系
 *
 */
public class ClassLoaderTest {

  public static void main(String[] args) {
    // 获取 系统类夹杂器（应用程序类加载器）
    ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
    // sun.misc.Launcher$AppClassLoader@18b4aac2
    System.out.println(sysClassLoader);


    // 获取上层，扩展类加载类
    ClassLoader extClassLoader = sysClassLoader.getParent();
    // sun.misc.Launcher$ExtClassLoader@61bbe9ba
    System.out.println(extClassLoader);


    //再次获取上层，尝试获取 引导类加载器
    ClassLoader bootStrapClassLoader = extClassLoader.getParent();
    // null, 结果是  不能获取到引导类加载器
    System.out.println(bootStrapClassLoader);

    // 获取用户自定义类的类加载器
    ClassLoader testClassLoader = ClassLoaderTest.class.getClassLoader();
    // sun.misc.Launcher$AppClassLoader@18b4aac2
    System.out.println(testClassLoader);

    //获取String的 类加载器
    ClassLoader stringClassLoader = String.class.getClassLoader();
    // null    间接证明string 类的类加载器是引导类加载器
    System.out.println(stringClassLoader);
  }
}
