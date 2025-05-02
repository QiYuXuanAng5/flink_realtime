//package flink.realtime.db.app;
//
//import com.struggle.flink.realtime.common.base.BaseApp;
//
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//
///**
// * @Package flink.realtime.db.app.DwdTradeOrderPaySucDetail
// * @Author guo.jia.hui
// * @Date 2025/4/15 20:04
// * @description: 支付成功事实表
// */
//public class DwdTradeOrderPaySucDetail extends BaseApp {
//    public static void main(String[] args) {
//        new DwdTradeOrderPaySucDetail().start(
//                10013,
//                4
//        );
//    }
//
//    @Override
//    public void handle(StreamExecutionEnvironment env, DataStreamSource<String> kafkaStrDS) {
//
//    }
//}
