package com.swpuiot.ws.lib.src.lib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuhaojie on 17-5-26.
 */
class Looper {

    private Looper() {
    }

    public interface CallBack {
        void onNewPoll(MessageQueue.QMessage message);
    }

    private static CallBack sCallBack;

    public static void prepare(CallBack callBack) {
        sCallBack = callBack;
    }

    public static void loop() {
        sExecutorService.submit(sLoopRunnable);
    }


    private static ExecutorService sExecutorService = Executors.newSingleThreadExecutor();

    private static LoopRunnable sLoopRunnable = new LoopRunnable();

    private static class LoopRunnable implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                MessageQueue.QMessage s = MessageQueue.take();
                if (sCallBack != null) sCallBack.onNewPoll(s);
            }
        }
    }

}
