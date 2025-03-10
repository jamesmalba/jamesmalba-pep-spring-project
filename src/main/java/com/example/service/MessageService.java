package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a message using message repository. 
     * Checks if message not blank, message not over 255 chars, and account posted by exists.
     *
     * @param message The message being added. 
     * @return Message The message object with the generated message id if added. Null otherwise. 
     */
    public Message createMessage(Message message) {
        String messageText = message.getMessageText();
        int postedBy = message.getPostedBy();
        if (messageText.isEmpty() || messageText.length() > 255) return null;
        if (!accountRepository.findByAccountId(postedBy).isPresent()) return null;
        return messageRepository.save(message);
    }

    /**
     * Gets all messages using message repository. 
     *
     * @return List<Message> List of messages.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Gets message by message id using message repository. 
     *
     * @param id Message id being searched. 
     * @return Message The message object if found. Null otherwise. 
     */
    public Message getMessageById(int id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            return message;
        }
        return null;
    }

    /**
     * Deletes a message by message id using message repository. 
     * 
     * @param id The message id being deleted. 
     * @return int The number of rows changed. 
     */
    public int deleteMessageById(int id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            int res = messageRepository.deleteMessageById(id);
            return res;
        }
        return 0;
    }

    /**
     * Update a message with updated messageText and id. 
     * Checks if updated message not blank, message not over 255 chars, and if message exists.
     *
     * @param message The message being added. 
     * @return int The number of rows changed. 
     */
    public int updateMessageById(int id, String messageText) {
        if (messageText.length() == 0) return 0;

        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            if (messageText.length() > 255) {
                return 0;
            }
            Message message = optionalMessage.get();
            message.setMessageText(messageText);
            return messageRepository.updateMessageById(messageText, id);
        }
        return 0;
    }

    /**
     * Get all messages by postedBy int. 
     *
     * @param id The postedBy id being searched. 
     * @return List<Message> The list of messages based on account id. 
     */
    public List<Message> getAllMessagesByAccountId(int id) {
        return messageRepository.findByPostedBy(id);
    }

}
