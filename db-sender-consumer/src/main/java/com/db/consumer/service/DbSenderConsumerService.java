package com.db.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class DbSenderConsumerService
{
    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "messageString", partitions = {"0-2"})
            })
    public void listen(String message)
    {
        System.out.println("Add new row to DB: " + message);
    }

    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "messageString", partitionOffsets = @PartitionOffset(partition = "3-9", initialOffset = "0"))
            })
    public void listen1(String message)
    {
        System.out.println("Add new row to DB: " + message);
    }
}