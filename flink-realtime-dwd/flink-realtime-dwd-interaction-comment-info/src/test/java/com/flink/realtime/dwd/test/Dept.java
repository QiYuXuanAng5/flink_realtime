package com.flink.realtime.dwd.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package com.flink.realtime.dwd.test.Dept
 * @Author guo.jia.hui
 * @Date 2025/4/11 16:21
 * @description: 部门
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dept {
    Integer deptno;
    String dname;
    Long ts;
}
