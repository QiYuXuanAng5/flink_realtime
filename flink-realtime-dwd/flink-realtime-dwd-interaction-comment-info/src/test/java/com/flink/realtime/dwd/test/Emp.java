package com.flink.realtime.dwd.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package com.flink.realtime.dwd.test.Emp
 * @Author guo.jia.hui
 * @Date 2025/4/11 16:18
 * @description: 员工
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emp {
    Integer empno;
    String ename;
    Integer deptno;
    Long ts;
}
