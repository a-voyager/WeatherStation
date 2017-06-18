package com.swpuiot.ws;

import com.swpuiot.ws.ds.FixedQueue;

import org.junit.Test;

import java.util.Random;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 00:06
 * Version: 1.0
 */

public class FixedQueueTest {
    @Test
    public void offer() throws Exception {

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            float v = i;
            mQueue.offer(v);
            System.out.print(v + " ");
        }
        System.out.println();
        System.out.println(mQueue.list());

    }

    @Test
    public void poll() throws Exception {

    }

    @Test
    public void list() throws Exception {

    }

    FixedQueue<Float> mQueue = new FixedQueue<>();

}
