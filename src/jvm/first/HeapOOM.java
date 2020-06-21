package jvm.first;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cunxiaopan
 * @Date: 2020/6/13 8:14 下午
 * @Description: VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * <p>堆内存溢出</p>
 */
public class HeapOOM {

  static class OOMObject {

  }

  public static void main(String[] args) {
    List<OOMObject> list = new ArrayList<>();
    while (true) {
      list.add(new OOMObject());
    }
  }
}
