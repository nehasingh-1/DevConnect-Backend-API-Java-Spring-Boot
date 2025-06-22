package com.devconnect.repository;

import com.devconnect.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
