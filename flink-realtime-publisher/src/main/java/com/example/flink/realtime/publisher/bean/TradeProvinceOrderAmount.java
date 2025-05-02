package flink;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Package com.example.flink.realtime.publisher.bean.TradeProvinceOrderAmount
 * @Author guo.jia.hui
 * @Date 2025/4/23 14:11
 * @description: 各省份交易额
 */
@Data
@AllArgsConstructor
public class TradeProvinceOrderAmount {
    // 省份名称
    String provinceName;
    // 各省份交易总额
    Double orderAmount;
}
