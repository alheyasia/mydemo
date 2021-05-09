package com.aref.test.mydemo.service;

import com.aref.test.mydemo.converter.MessageConverter;
import com.aref.test.mydemo.model.MessageRO;
import com.aref.test.mydemo.model.MessageVO;
import com.aref.test.mydemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private PalindromeService palindromeService;

    public List<MessageVO> getMessages() {
        List<MessageRO> messageROS = messageRepository.findAll();
        List<MessageVO> messageVOS = messageConverter.convertMessageROsToVOs(messageROS);
        return addLongestPalindromeTO(messageVOS);
    }

    private List<MessageVO> addLongestPalindromeTO(List<MessageVO> messageVOS) {
        for (MessageVO  messageVO: messageVOS) {
            messageVO.setLongest_palindrome_size(palindromeService.calculateLongestPalindrome(messageVO.getContent()));
        }
        return messageVOS;
    }
}
