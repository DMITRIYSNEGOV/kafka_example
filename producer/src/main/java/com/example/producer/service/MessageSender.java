package com.example.producer.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MessageSender
{
    public static final String TOPIC_NAME_1 = "messageString";

    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message)
    {
        val future = kafkaTemplate.send(TOPIC_NAME_1, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
        {
            @Override
            public void onFailure(Throwable ex)
            {
                log.error("fail to send to consumer \"{}\" reason:\n{}", message, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result)
            {
                log.info("send to consumer \"{}\" by offset: {} by partition: {}", message, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }
        });
    }
}
