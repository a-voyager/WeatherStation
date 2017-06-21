package com.swpuiot.ws.lib.src.lib.exception;

/**
 * Created by wuhaojie on 17-5-26.
 */
public class MessageQueueException extends RuntimeException {

    public MessageQueueException() {
        super("消息队列发生严重错误, 请重启程序...");
    }
}
