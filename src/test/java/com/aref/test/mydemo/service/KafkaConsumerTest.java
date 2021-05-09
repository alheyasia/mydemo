//package com.aref.test.mydemo.service;
//
//import com.aref.test.mydemo.model.MessageRO;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.test.EmbeddedKafkaBroker;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.kafka.test.utils.KafkaTestUtils;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(properties =
//        "spring.kafka.bootstrapServers:${" + EmbeddedKafkaBroker.SPRING_EMBEDDED_KAFKA_BROKERS + "}")
//@DirtiesContext
//@EmbeddedKafka(topics = {"myTopic"},partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093"})
//class KafkaConsumerTest {
//
//
//    @Value("${kafka.topic}")
//    private String topic;
//
//
//    @Autowired
//    private KafkaProducer producer;
//
//    @Autowired
//    private KafkaConsumer consumer;
//
//    @Autowired
//    private EmbeddedKafkaBroker embeddedKafkaBroker;
//
////    static {
////        System.setProperty(EmbeddedKafkaBroker.BROKER_LIST_PROPERTY,
////                "spring.kafka.bootstrap-servers");
////    }
//
//    @Test
//    void getMessage_shouldReceiveMessageFromKafkaProducer() throws Exception {
//        String date = "2018-10-09 00:12:12+0100";
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
//        Date parsedDate = format.parse(date);
//        MessageRO messageRO = new MessageRO();
//        messageRO.setContent("test");
//        messageRO.setTimestamp(parsedDate);
//
////        Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
////        senderProps.put(ProducerConfig.RETRIES_CONFIG, 1);
////        DefaultKafkaProducerFactory<String, MessageRO> pf = new DefaultKafkaProducerFactory<>(senderProps);
////        KafkaTemplate<String, MessageRO> template = new KafkaTemplate<>(pf);
////        template.setDefaultTopic(TOPIC);
//
//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("myGroup", "false", embeddedKafkaBroker);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        ConsumerFactory cf = new DefaultKafkaConsumerFactory<String, MessageRO>(consumerProps, new StringDeserializer(), new JsonDeserializer<>(MessageRO.class));
//        Consumer<String, MessageRO> consumerServiceTest = cf.createConsumer();
//
//        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, topic);
//
//        // WHEN
//        producer.send(messageRO);
//
//        // THEN
//        ConsumerRecord<String, MessageRO> consumerRecordOfMessageRO = KafkaTestUtils.getSingleRecord(consumerServiceTest, topic);
////        ConsumerRecords<String, MessageRO> records = KafkaTestUtils.getRecords(consumerServiceTest, 1000);
//
//        MessageRO value = consumerRecordOfMessageRO.value();
//        assertEquals("test", value.getContent());
//
//        consumerServiceTest.close();
//    }
//}