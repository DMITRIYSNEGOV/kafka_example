package com.mail.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class MailSenderConsumerService
{
    @KafkaListener(
    topicPartitions = {
            @TopicPartition(topic = "messageString", partitions = {"0", "1", "2"})
    })
    public void listen(String message)
    {
        System.out.println("Mail sent: " + message);
    }
}
