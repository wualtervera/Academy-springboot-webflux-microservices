package com.devcreativa.mscourse.util;

import com.google.gson.Gson;
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

    public void printLog(String traceName, String tagKey, Object tagValue){

        String json = objectToJson(tagValue);

        Span span = tracer.nextSpan()
            .name(traceName)
            .tag(tagKey, json);

        try(Tracer.SpanInScope si = tracer.withSpan(span.start())){
            log.info(tagKey.concat(" -> {}"), json);
        }finally {
            span.end();
        }

    }

    public String objectToJson(Object object){
        Gson gson = new Gson();

        String json =  gson.toJson(object);
        return json;
    }


}
