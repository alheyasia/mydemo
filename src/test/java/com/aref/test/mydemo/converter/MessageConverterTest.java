package com.aref.test.mydemo.converter;

import com.aref.test.mydemo.model.MessageRO;
import com.aref.test.mydemo.model.MessageVO;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageConverterTest {

    public static final String CONTENT = "abrakadabra";

    MessageConverter messageConverter = new MessageConverter();

    @Test
    public void convertMessageROsToVOs_shouldReturnEmptyList_whenMessagesNull() {
        List<MessageVO> messageVOS = messageConverter.convertMessageROsToVOs(null);

        assertEquals(Collections.emptyList(), messageVOS);
    }

    @Test
    public void convertMessageROsToVOs_shouldReturnMessageVOs() throws Exception {
        MessageRO messageRO = createMessageRO(CONTENT, "2018-10-09 00:12:12+0100");
        List<MessageVO> messageVOS = messageConverter.convertMessageROsToVOs(Collections.singletonList(messageRO));

        assertEquals(1, messageVOS.size());
        MessageVO messageVO = messageVOS.get(0);
        assertEquals(messageRO.getContent(), messageVO.getContent());
        assertEquals(messageRO.getTimestamp(), messageVO.getTimestamp());
    }

    private MessageRO createMessageRO(String content, String date) throws Exception {
        MessageRO messageRO = new MessageRO();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        Date parsedDate = format.parse(date);
        messageRO.setContent(content);
        messageRO.setTimestamp(parsedDate);
        return messageRO;

    }

}