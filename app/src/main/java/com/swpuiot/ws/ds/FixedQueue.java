package com.swpuiot.ws.ds;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/18 23:58
 * Version: 1.0
 */

public class FixedQueue<T> {

    private int mCapacity = 10;
    private int offerCount = 0;
    private LinkedList<T> mQueue;

    public FixedQueue() {
        init();
    }

    public FixedQueue(int capacity) {
        mCapacity = capacity;
        init();
    }

    private void init() {
        mQueue = new LinkedList<>();
    }

    public boolean offer(T t) {
        if (mQueue.size() == mCapacity) {
            mQueue.poll();
        }
        offerCount++;
        return mQueue.offer(t);
    }

    public T poll() {
        return mQueue.poll();
    }

    public List<T> list() {
        return mQueue;
    }

    public int getOfferCount() {
        return offerCount;
    }

    public int getCapacity() {
        return mCapacity;
    }
}
