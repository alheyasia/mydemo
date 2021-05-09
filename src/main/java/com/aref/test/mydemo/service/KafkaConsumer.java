package com.aref.test.mydemo.service;

import com.aref.test.mydemo.model.MessageRO;
import com.aref.test.mydemo.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = "${kafka.topic}", groupId = "myGroup")
    public MessageRO getMessage(MessageRO message) {
        LOGGER.info("message received with content: " + message.getContent() + " and topic is " + topic);
        messageRepository.save(message);
        LOGGER.info("message persisted to db: " + message.getContent() + " and topic is " + topic);
        simpMessagingTemplate.convertAndSend("/webtopic/message", message);
        return message;
    }
}
