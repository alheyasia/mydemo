package com.aref.test.mydemo.service;

import com.aref.test.mydemo.model.MessageRO;
import com.aref.test.mydemo.model.MessageVO;
import com.aref.test.mydemo.repository.MessageRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties =
                "spring.kafka.bootstrapServers:${" + EmbeddedKafkaBroker.SPRING_EMBEDDED_KAFKA_BROKERS + "}")
@EmbeddedKafka(topics = {"myTopic"}, partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093"})
public class MessageIT {

    private static final String RESPONSE_PATH = "Messages/response.json";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

//    @Test
//    @DirtiesContext
//    public void sendMessage_shouldPersistMessageToDB() throws Exception {
//        String date = "2018-10-09 00:12:12+0100";
//        MessageRO messageRO = createMessageRO("testcontent", date);
//
//        String API_URL = "http://localhost:" + randomServerPort + "/message";
//
//        ResponseEntity<MessageRO> messageROResponseEntity = restTemplate.postForEntity(API_URL, messageRO, MessageRO.class);
//        assertEquals(HttpStatus.OK, messageROResponseEntity.getStatusCode());
//        assertEquals("testcontent", messageROResponseEntity.getBody().getContent());
//        assertEquals(messageRO.getTimestamp(), messageROResponseEntity.getBody().getTimestamp());
//
//        List<MessageRO> all = messageRepository.findAll();
//        assertEquals(1, all.size());
//        MessageRO messageRO1 = all.get(0);
//
//        assertEquals("testcontent", messageRO1.getContent());
//        assertEquals(messageRO.getTimestamp(), messageRO1.getTimestamp());
//
//        messageRepository.deleteAll();
//
//    }

    @Test
    @DirtiesContext
    public void getMessages_shouldFetchPersistedMessages() throws Exception {
        MessageRO messageRO1 = createMessageRO("abrakadabra", "2018-10-09 00:12:12+0100");
        MessageRO messageRO2 = createMessageRO("a", "2019-10-09 00:12:12+0100");
        MessageRO messageRO3 = createMessageRO("abb", "2020-10-09 00:12:12+0100");

        String API_URL = "http://localhost:" + randomServerPort + "/message";

        restTemplate.postForEntity(API_URL, messageRO1, MessageRO.class);
        restTemplate.postForEntity(API_URL, messageRO2, MessageRO.class);
        restTemplate.postForEntity(API_URL, messageRO3, MessageRO.class);

        ResponseEntity<List<MessageVO>> responseEntity = restTemplate.exchange(API_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<MessageVO>>() {
        });

        List<MessageVO> responseEntityBody = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, responseEntityBody.size());

        List<MessageVO> expectedResult = readResponseFromPath(RESPONSE_PATH);

        JSONAssert.assertEquals(objectMapper.writeValueAsString(expectedResult), objectMapper.writeValueAsString(responseEntityBody), JSONCompareMode.STRICT);

        messageRepository.deleteAll();
    }

    private MessageRO createMessageRO(String content, String date) throws ParseException {
        MessageRO messageRO = new MessageRO();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        Date parsedDate = format.parse(date);
        messageRO.setContent(content);
        messageRO.setTimestamp(parsedDate);
        return messageRO;

    }

    private MessageRO createMessageRO2(String content, String date) throws ParseException {
        MessageRO messageRO = new MessageRO();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = format.parse(date);
        messageRO.setContent(content);
        messageRO.setTimestamp(parsedDate);
        return messageRO;

    }

    private List<MessageVO> readResponseFromPath(String path) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + RESPONSE_PATH);
        return objectMapper.readValue(resource.getFile(), new TypeReference<List<MessageVO>>() {
        });
    }

}
