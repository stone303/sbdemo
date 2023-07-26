package com.example.sbdemo.model;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.platform.commons.util.StringUtils;

/**
 * @Description:  自定义日志格式化
 * @Param:
 * @return:
 * @Author: zuozewei
 * @Date: 2021/3/26
 *
 */
public class FlagPatternConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String flag = FlagTrackInterceptor.getFlag();
        return StringUtils.isBlank(flag) ? "flag" : flag;
    }
}
