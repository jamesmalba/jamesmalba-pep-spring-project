package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Create new message 
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    // Get all messagaes
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Get one message by message id
    public Message getMessageById(int id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            return message;
        }
        return null;
    }

    // Delete message by message id 
    public int deleteMessageById(int id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            int res = messageRepository.deleteMessageById(id);
            return res;
        }
        return 0;
    }

    // Update message by message id
    public int updateMessageById(int id, String messageText) {
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

    // Get all messages by account id 
    public List<Message> getAllMessagesByAccountId(int id) {
        return messageRepository.findByPostedBy(id);
    }

}
