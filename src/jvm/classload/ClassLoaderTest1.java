package jvm.classload;

import java.net.URL;
import java.security.Provider;
import sun.misc.Launcher;

/**
 * @Author: cunxiaopan
 * @Date: 2020/09/04 10:01
 * @Description:
 */
public class ClassLoaderTest1 {

  public static void main(String[] args) {
    System.out.println("------ 引导类加载器 ------");
    URL[] urls = Launcher.getBootstrapClassPath().getURLs();
    for (URL url : urls) {
      System.out.println(url.toExternalForm());
    }
    ClassLoader classLoader = Provider.class.getClassLoader();
    // null 证明 jre/lib/jsse.jar 里面的 Provider 类确实是 引导类加载器加载的
    System.out.println(classLoader);

    System.out.println("------ 扩展类加载器 ------");
    String extDir = System.getProperty("java.ext.dirs");
    for (String str : extDir.split(":")) {
      System.out.println(str);
    }
  }
}
