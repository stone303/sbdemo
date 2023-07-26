package com.example.sbdemo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * @Description: 标记追踪拦截器
 * @Param:
 * @return:
 * @Author:
 * @Date: 2021/3/15
 * 创建一个Java类，并实现HandlerInterceptor接口。
 * 在实现类中重写HandlerInterceptor接口中的方法，常用的方法有：
 * preHandle: 在请求处理之前进行拦截，可以在这里进行权限验证、日志记录、请求参数解析等操作，并返回一个boolean值来决定是否继续处理该请求。
 * postHandle: 在请求处理之后、视图渲染之前进行拦截，可以在这里修改响应结果、添加模型数据等操作。但不能修改请求本身。
 * afterCompletion: 在请求处理完成后进行拦截，可以在这里进行一些资源清理操作或者记录请求结束日志等。
 *
 */

@Component
public class FlagTrackInterceptor implements HandlerInterceptor {
    /**
     * 存储 flag
     * ThreadLocal，很多地方叫做线程本地变量，也有些地方叫做线程本地存储，其实意思差不多。ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量
     */
    private static final ThreadLocal<String> FLAG_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取请求头 header 中传递的 flag，若没有，则 UUID 代替
         */
        String flag = Optional.ofNullable(request.getHeader("flag")).orElse(UUID.randomUUID().toString().replaceAll("-",""));
        // 请求前设置
        FLAG_THREAD_LOCAL.set(flag);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除，防止内存泄漏
        FLAG_THREAD_LOCAL.remove();
    }

    public static String getFlag() {
        return FLAG_THREAD_LOCAL.get();
    }

    public static void setFlag(String flag){
        FLAG_THREAD_LOCAL.set(flag);
    }
}
