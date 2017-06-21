package com.swpuiot.ws.lib.src.lib.callback;

/**
 * Interface for new message comes.
 * Created by wuhaojie on 17-5-25.
 */
public interface MessageCallBack<T> {

    /**
     * Calls when new message arrived.
     * @param message the message of client send.
     */
    void onNewMessage(T message);

}
