package com.aref.test.mydemo.service;

import com.aref.test.mydemo.model.MessageRO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, MessageRO> template;

    public MessageRO send(MessageRO message) {
        LOGGER.info("message sent with content: " + message.getContent() + " and topic is " + topic);
        template.send(topic, message);
        return message;
    }
}
