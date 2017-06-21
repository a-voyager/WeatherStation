package com.swpuiot.ws.lib.src.lib;


import com.swpuiot.ws.lib.src.lib.exception.MessageQueueException;
import com.swpuiot.ws.lib.src.lib.utils.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Message Queue
 * Created by wuhaojie on 17-5-26.
 */
class MessageQueue {

    private MessageQueue() {
    }

    static class QMessage {
        String topic;
        String msg;

        public QMessage(String topic, String msg) {
            this.topic = topic;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "QMessage{" +
                    "topic='" + topic + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    private static BlockingQueue<QMessage> mQueue = new LinkedBlockingQueue<>();
    private static ExecutorService sExecutorService = Executors.newSingleThreadExecutor();

    private static class QueueSubmitRunnable implements Runnable {

        private long delay = 0;
        private QMessage msg;

        private QueueSubmitRunnable() {
        }

        public void setMsg(QMessage msg) {
            this.msg = msg;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                mQueue.put(msg);
            } catch (InterruptedException e) {
                Log.e(e);
            }
        }
    }


    public static void put(QMessage msg) {
        put(msg, 0);
    }


    public static void put(QMessage msg, long delay) {
        QueueSubmitRunnable runnable = new QueueSubmitRunnable();
        runnable.setMsg(msg);
        runnable.setDelay(delay);
        sExecutorService.submit(runnable);
    }

    public static QMessage take() {
        try {
            return mQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new MessageQueueException();
    }


}
