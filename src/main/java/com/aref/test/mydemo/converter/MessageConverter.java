package com.aref.test.mydemo.converter;

import com.aref.test.mydemo.model.MessageRO;
import com.aref.test.mydemo.model.MessageVO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MessageConverter {

    public List<MessageVO> convertMessageROsToVOs(List<MessageRO> messageROS) {
        return Optional.ofNullable(messageROS)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::convertMessageRoToVo)
                .collect(Collectors.toList());

    }

    private MessageVO convertMessageRoToVo(MessageRO messageRO) {
        MessageVO messageVO = new MessageVO();
        messageVO.setContent(messageRO.getContent());
        messageVO.setTimestamp(messageRO.getTimestamp());
        return messageVO;
    }

}
