package com.example.serialnumbergenerator.test.custom;


import com.example.serialnumbergenerator.custom.IdWorkerCustom;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;


@SpringBootTest
@Slf4j
public class IdWorkerCustomTest {

    @Autowired
    private IdWorkerCustom idWorkerCustom;

    @Test
    public void test(){
        //模拟100个线程同时执行
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i=0;i<100;i++){
            Thread thread = new MyThread(countDownLatch);
            thread.start();
            countDownLatch.countDown();
        }
    }

    class  MyThread extends Thread{

        private CountDownLatch countDownLatch;

        public MyThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                log.info("count:{},id:{}", countDownLatch.getCount(),idWorkerCustom.nextId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

