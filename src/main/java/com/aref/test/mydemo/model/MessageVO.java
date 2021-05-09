package com.aref.test.mydemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageVO {

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private Date timestamp;

    private int longest_palindrome_size;

    public MessageVO() {
    }

    public MessageVO(String content, Date timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getLongest_palindrome_size() {
        return longest_palindrome_size;
    }

    public void setLongest_palindrome_size(int longest_palindrome_size) {
        this.longest_palindrome_size = longest_palindrome_size;
    }
}
