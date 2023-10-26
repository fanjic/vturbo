package com.fan.vturbo.example.BIO.fakeAsynch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerServerSocketPool {

    private ExecutorService executorService;

    /*线程池对象
    *MICROSECONDS(微秒)、MILLISECONDS(毫秒)
    * public ThreadPoolExecutor(int corePoolSize,//核心线程，保留的线程数量
                              int maximumPoolSize,//最大的线程数量
                              long keepAliveTime,//线程保留时间
                              TimeUnit unit,//保留时间单位
                              BlockingQueue<Runnable> workQueue) //任务队列
    * */
    public HandlerServerSocketPool(int maxThreadNum,int queueSize){
        executorService=new ThreadPoolExecutor(
                2,maxThreadNum,60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable target){
        executorService.execute(target);
    }

}
