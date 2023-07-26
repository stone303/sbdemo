package com.example.sbdemo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guocang.shi
 *
 * 在 Java 中，关键字 synchronized 用于修饰方法或代码块，以实现多线程的同步。当一个方法或代码块被
 * synchronized 修饰时，同一时刻只有一个线程可以进入这个方法或代码块进行执行，其他线程需要等待。
 * 在你提供的代码中，addResponse 方法被使用 synchronized 修饰，意味着同时只能有一个线程访问
 * addResponse 方法。这样做的目的是为了保证在多线程环境下对 responseMap 的操作是同步和线程安全的。
 * 如果没有使用 synchronized 关键字修饰 addResponse 方法，那么多个线程可以并发地执行该方法，并在没有同步机制的情况下对
 * responseMap 进行操作。这可能导致不一致的结果，例如数据丢失、覆盖等问题，因为多个线程同时对 responseMap 进行读写。
 */
public class ResponsePool {

    private static ResponsePool instance;
    private Map<String, Object> responseMap;

    private ResponsePool() {
        responseMap = new HashMap<>();
    }
  /***getInstance()：静态同步方法，用于获取ResponsePool类的单例实例。如果instance为空，
   *则创建一个新的实例；如果instance已经存在，则直接返回现有的实例。
   * ***/
    public static synchronized ResponsePool getInstance() {
        if (instance == null) {
            instance = new ResponsePool();
        }
        return instance;
    }

    /***
     * 同步方法，用于将响应结果添加到响应池中。接受两个参数，key为标识符，response为响应结果。
     * @param key
     * @param response
     */
    public synchronized void addResponse(String key, Object response) {
        responseMap.put(key, response);
    }

    /***
     * 同步方法，用于从响应池中获取指定标识符的响应结果。接受一个参数key，返回对应的响应结果。
     * @param key
     * @return
     */
    public synchronized Object getResponse(String key) {
        System.out.print(responseMap.size());
        return responseMap.get(key);
    }

    /***
     * removeResponse(String key)：同步方法，用于从响应池中移除指定标识符的响应结果。接受一个参数key，移除对应的响应结果。
     * @param key
     */
    public synchronized void removeResponse(String key) {
        responseMap.remove(key);
    }

}
