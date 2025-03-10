package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Transactional 
    @Query("DELETE FROM Message m WHERE m.id = :id")
    int deleteMessageById(@Param("id") int id);

    @Modifying
    @Transactional 
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.id = :id")
    int updateMessageById(@Param("messageText") String messageText, @Param("id") int id);

    List<Message> findByPostedBy(int postedBy);
}
