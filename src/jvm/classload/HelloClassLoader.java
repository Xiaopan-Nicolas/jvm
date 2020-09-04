package jvm.classload;

/**
 * @Author: cunxiaopan
 * @Date: 2020/09/01 17:49
 * @Description: 查看字节码文件，发现初始化是按照顺序执行的，会覆盖前值
 */
public class HelloClassLoader {

  private static int num = 1;

  static {
    num = 2;
    number = 3;
    // System.out.println(number); // 非法前向引用
  }

  private static int number = 1;

  public static void main(String[] args) {
    System.out.println(HelloClassLoader.num);
    System.out.println(HelloClassLoader.number);
  }
}
