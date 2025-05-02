package com.example.flink.realtime.publisher.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Author guo.jia.hui
 * @Date 2025/4/22 16:38
 * @description: 1
 */
public class DateFormatUtil {
    public static Integer now(){
        String yyyyMMdd = DateFormatUtils.format(new Date(), "yyyyMMdd");
        return Integer.valueOf(yyyyMMdd);
    }
}
