package jvm.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 *
 * @Author: cunxiaopan
 * @Date: 2020/09/04 14:37
 * @Description: 实现从某个文件读取字节流，加载该类的功能
 */
public class CustomClassLoader extends ClassLoader {

  private String path;

  public CustomClassLoader(String path) {
    this.path = path;
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    try {
      byte[] bytes = getClassFromCustomPath(name);
      return defineClass(name, bytes, 0, bytes.length);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return super.findClass(name);
  }

  private byte[] getClassFromCustomPath(String name) throws IOException {
    // 从自定义路径中加载指定类
    //如果指定路径的字节码文件进行了加密，则需要在此方法中解密
    String fileName = name.replace(".", "/");
    fileName = path + fileName + ".class";
    InputStream is = null;
    ByteArrayOutputStream outputStream = null;
    try {
      is = new FileInputStream(new File(fileName));
      outputStream = new ByteArrayOutputStream();
      int i = 0;
      while ((i = is.read()) != -1) {
        outputStream.write(i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (outputStream != null) {
        outputStream.close();
      }
      if (is != null) {
        is.close();
      }
    }
    return outputStream.toByteArray();
  }

  public static void main(String[] args) {
    // class 文件位置 目录 /Users/xiao/projects/jvm/other/Hello.class
    String path = System.getProperty("user.dir") + "/other/";
    System.out.println(path);
    CustomClassLoader customClassLoader = new CustomClassLoader(path);
    try {
      String name = "Hello";

      Class<?> clazz2 = customClassLoader.loadClass(name);
      Object obj2 = clazz2.newInstance();
      System.out.println(obj2.getClass().getClassLoader());

      Class<?> clazz = Class.forName(name, true, customClassLoader);
      Object obj = clazz.newInstance();
      System.out.println(obj.getClass().getClassLoader());

      /*for (int i = 0; i < clazz.getMethods().length; i++) {
        System.out.println(clazz.getMethods()[i].getName());
      }
      System.out.println("==========================");
      for (int i = 0; i < clazz.getDeclaredMethods().length; i++) {
        System.out.println(clazz.getDeclaredMethods()[i].getName());
      }*/
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
  }

}
