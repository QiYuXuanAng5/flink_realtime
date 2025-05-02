package com.example.flink.realtime.publisher.service;

import com.example.flink.realtime.publisher.bean.TradeProvinceOrderAmount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易域统计service接口
 */
public interface TradeStateService {
    //获取某天总交易额
    BigDecimal getGMV(Integer date);
    //获取某天各个身份交易额
    List<TradeProvinceOrderAmount> getProvinceAmount(Integer date);
}
