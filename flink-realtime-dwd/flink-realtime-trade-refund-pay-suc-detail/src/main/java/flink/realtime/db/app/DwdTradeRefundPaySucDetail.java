package flink.realtime.db.app;

import com.struggle.flink.realtime.common.base.BaseSQLApp;
import com.struggle.flink.realtime.common.constant.Constant;

import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.time.Duration;

/**
 * @Package flink.realtime.db.app.DwdTradeRefundPaySucDetail
 * @Author guo.jia.hui
 * @Date 2025/5/5 11:26
 * @description:
 */
public class DwdTradeRefundPaySucDetail extends BaseSQLApp {
    public static void main(String[] args) {
        new DwdTradeRefundPaySucDetail().start(
                10016,
                4
        );
    }

    @Override
    public void handle(StreamTableEnvironment tableEnv) {
        tableEnv.getConfig().setIdleStateRetention(Duration.ofSeconds(5));

        // 1. 读取 topic_db
        readOdsDb(tableEnv, Constant.TOPIC_DWD_TRADE_REFUND_PAYMENT_SUCCESS);
        // 2. 读取 字典表
        readBaseDic(tableEnv);

        // 3. 过滤退款成功表数据
        Table refundPayment = tableEnv.sqlQuery(
                "select " +
                        "after['id'] id," +
                        "after['order_id'] order_id," +
                        "after['sku_id'] sku_id," +
                        "after['payment_type'] payment_type," +
                        "after['callback_time'] callback_time," +
                        "after['total_amount'] total_amount," +
                        "ts_ms," +
                        "TO_TIMESTAMP(FROM_UNIXTIME(ts_ms/1000)) AS proc_time," +
                        "PROCTIME() AS proc_time " +
                        "from topic_db " +
                        "where source['table'] = 'refund_payment' " +
                        "and `op`='u' " +
                        "and `before`['refund_status'] is not null " +
                        "and `after`['refund_status']='1602'");
        tableEnv.createTemporaryView("refund_payment", refundPayment);

        //tableEnv.executeSql("select * from refund_payment").print();

        // 4. 过滤退单表中的退单成功的数据
        Table orderRefundInfo = tableEnv.sqlQuery(
                "select " +
                        "after['order_id'] order_id," +
                        "after['sku_id'] sku_id," +
                        "after['refund_num'] refund_num " +
                        "from topic_db " +
                        "where source['table'] = 'order_refund_info' " +
                        "and `op`='u' " +
                        "and `before`['refund_status'] is not null " +
                        "and `after`['refund_status']='0705'");
        tableEnv.createTemporaryView("order_refund_info", orderRefundInfo);

        //tableEnv.executeSql("select * from order_refund_info").print();

        // 5. 过滤订单表中的退款成功的数据
        Table orderInfo = tableEnv.sqlQuery(
                "select " +
                        "after['id'] id," +
                        "after['user_id'] user_id," +
                        "after['province_id'] province_id " +
                        "from topic_db " +
                        "where source['table'] = 'order_info' " +
                        "and `op`='u' " +
                        "and `before`['order_status'] is not null " +
                        "and `after`['order_status']='1006'");
        tableEnv.createTemporaryView("order_info", orderInfo);

        //tableEnv.executeSql("select * from order_info").print();

//        //6. 4 张表的 join
//        Table result = tableEnv.sqlQuery(
//                "select " +
//                        "rp.id, " +
//                        "oi.user_id, " +
//                        "rp.order_id, " +
//                        "rp.sku_id " +
//                        "from refund_payment rp " +
//                        "join order_refund_info ori " +
//                        "   on rp.order_id = ori.order_id and rp.sku_id = ori.sku_id " +
//                        "join order_info oi " +
//                        "   on rp.order_id = oi.id " +
//                        "join base_dic dic " +  // 普通 JOIN
//                        "   on rp.payment_type = dic.dic_code"
//        );
//        result.execute().print();

//        // 7.写出到 kafka
//        tableEnv.executeSql("create table " + Constant.TOPIC_DWD_TRADE_REFUND_PAYMENT_SUCCESS + "(" +
//                "id string," +
//                "user_id string," +
//                "order_id string," +
//                "sku_id string," +
//                "province_id string," +
//                "payment_type_code string," +
//                "payment_type_name string," +
//                "date_id string," +
//                "callback_time string," +
//                "refund_num string," +
//                "refund_amount string," +
//                "ts bigint ," +
//                "PRIMARY KEY (id) NOT ENFORCED " +
//                ")" + SQLUtil.getUpsertKafkaDDL(Constant.TOPIC_DWD_TRADE_REFUND_PAYMENT_SUCCESS));
//        result.executeInsert(Constant.TOPIC_DWD_TRADE_REFUND_PAYMENT_SUCCESS);
    }
}
