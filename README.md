# Flink 实时数据处理平台

**这是一个flink实施项目！包含了从ods到ads的处理，还有springboot和百度sugar的使用**

## 🏗️ 项目结构
```text
flink_realtime/                          # 父项目（聚合模块）
├── flink-realtime-common/               # 公共工具模块
├── flink-realtime-dim/                  # 维度数据处理模块
├── flink-realtime-dwd/                  # 明细数据层处理模块
├── flink-realtime-dws/                  # 汇总数据层处理模块
└── flink-realtime-publisher/            # 数据发布模块
```

## 项目使用工具
* Mysql
* FlinkCDC
* Hbase
* idea
* Offset Explorer
* kafka
* SpringBoot
* Sugar
* FineReport

## 项目细节 
这是DIM层test里FlinkCDC的代码,需要注意写该prop配置  
Properties prop = new Properties();  
prop.put("decimal.handling.mode", "string");  
prop.put("connect.decimal.precision", "16");  
prop.put("connect.decimal.scale", "2");  
一开始没写，写到后面  
"split_activity_amount": "0.00",  
"split_coupon_amount": "0.00",  
"split_total_amount": "69.00",  
这里将不再是decimal类型，它由于FlinkCDC的特性，导致不能读取decimal类型，会产生类似乱码的效果，需要注意