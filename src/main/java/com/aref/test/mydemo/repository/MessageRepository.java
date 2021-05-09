package com.aref.test.mydemo.repository;

import com.aref.test.mydemo.model.MessageRO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageRO, Long> {
}
