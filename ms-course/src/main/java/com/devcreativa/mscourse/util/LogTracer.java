package com.devcreativa.mscourse.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LogTracer {

    @Autowired
    private Tracer tracer;

    public void printLog(String trace, Object object){

        Span span = tracer.nextSpan().name(trace);
        try(Tracer.SpanInScope si = tracer.withSpan(span.start())){
            log.info(trace.concat(" -> {}"), object);
        }finally {
            span.end();
        }

    }

}
