package com.example.serialnumbergenerator.custom;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义id生成
 */
@Component
public class IdWorkerCustom {

    //生成的序号
    private AtomicInteger sequence = new AtomicInteger();

    private String lastTimestamp = "";

    private Integer value = 4;

    private String prefix = "000";


    public synchronized String nextId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        if(!this.lastTimestamp.equals(timestamp)) {
            sequence.set(0);
        }
        this.lastTimestamp = timestamp;
        return timestamp + fixedLength();
    }

    private String fixedLength() {
        String seq = prefix + sequence.incrementAndGet();
        if(seq.length() > value) {
            return seq.substring(seq.length() - value, seq.length());
        }
        return seq;
    }
}
