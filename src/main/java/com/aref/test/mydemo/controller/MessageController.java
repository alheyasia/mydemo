package com.aref.test.mydemo.controller;

import com.aref.test.mydemo.model.MessageRO;
import com.aref.test.mydemo.model.MessageVO;
import com.aref.test.mydemo.service.KafkaProducer;
import com.aref.test.mydemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private MessageService messageService;

    @PostMapping
    @ResponseBody
    public MessageRO sendMessage(@Valid @RequestBody MessageRO message) {
        producer.send(message);
        return message;
    }

    @GetMapping
    @ResponseBody
    public List<MessageVO> getMessages() {
        return messageService.getMessages();
    }


}
