package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repository for handling database requests based on messages.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Deletes a message by its message id.
     *
     * @param id The message id to be deleted.
     * @return The number of updated rows.
     */
    @Modifying
    @Transactional 
    @Query("DELETE FROM Message m WHERE m.id = :id")
    int deleteMessageById(@Param("id") int id);

    /**
     * Updates a message by its message id.
     *
     * @param messageText, id The message text to be updated and its id.
     * @return The number of updated rows.
     */
    @Modifying
    @Transactional 
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.id = :id")
    int updateMessageById(@Param("messageText") String messageText, @Param("id") int id);

    /**
     * Finds all messages by postedBy.
     *
     * @param postedBy The account id to find all messages.
     * @return The list of messages by account id.
     */
    List<Message> findByPostedBy(int postedBy);
}
