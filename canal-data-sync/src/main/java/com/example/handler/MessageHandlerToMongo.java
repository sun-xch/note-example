package com.example.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("messageHandlerToMongo")
public class MessageHandlerToMongo implements MessageHandler{

    //行数据日志
    private static String row_format = "binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {} , delay : {}ms";
    //事务日志
    private static String transaction_format = "binlog[{}:{}] , executeTime : {} , delay : {}ms";
    //批处理执行日志
    private static String execute_format = "command execute over,available:{} insert:{} update:{} remove:{}";
    //数据存储耗时日志
    private static String execute_time_format = "executeTime ,overData:{}ms overNaive:{}ms overComplete:{}ms";

    @Override
    public boolean execute(List<CanalEntry.Entry> entrys) throws Exception {

        //遍历数据
        for (CanalEntry.Entry entry : entrys) {
            long executeTime = entry.getHeader().getExecuteTime();
            long startTime = System.currentTimeMillis();
            long delayTime = startTime - executeTime;

            //打印事务开始结束信息
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN) {
                    CanalEntry.TransactionBegin begin;
                    try {
                        begin = CanalEntry.TransactionBegin.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务头信息，事务耗时
                    log.info(transaction_format, entry.getHeader().getLogfileName(), String.valueOf(entry.getHeader().getLogfileOffset()),
                            String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime));
                    log.info(" BEGIN ----> Thread id: {}", begin.getThreadId());
                } else if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                    CanalEntry.TransactionEnd end;
                    try {
                        end = CanalEntry.TransactionEnd.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务提交信息，事务id
                    log.info(" END ----> transaction id: {}", end.getTransactionId());
                    log.info(transaction_format, entry.getHeader().getLogfileName(), String.valueOf(entry.getHeader().getLogfileOffset()),
                            String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime));
                }
                continue;
            }

            //保存事务内变动数据
            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                CanalEntry.RowChange rowChage;
                try {
                    rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }
                CanalEntry.EventType eventType = rowChage.getEventType();
                log.info(row_format, entry.getHeader().getLogfileName(), String.valueOf(entry.getHeader().getLogfileOffset()),
                        entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                        eventType, String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime));
                if (eventType == CanalEntry.EventType.ERASE || eventType == CanalEntry.EventType.TRUNCATE) {
                    log.info(" sql ----> " + rowChage.getSql());
                    //包含清表操作，不做处理返回false
                    return false;
                } else if (eventType == CanalEntry.EventType.QUERY || rowChage.getIsDdl()) {
                    log.info(" sql ----> " + rowChage.getSql());
                    continue;
                }
                for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                    String schemaName = entry.getHeader().getSchemaName();
                    String tableName = entry.getHeader().getTableName();
                    if (eventType == CanalEntry.EventType.DELETE) {

                    } else if (eventType == CanalEntry.EventType.INSERT) {

                    } else if (eventType == CanalEntry.EventType.UPDATE) {

                    } else {
                        log.info("未知数据变动类型：{}", eventType);
                    }
                }
            }

        }

        return true;
    }
}
