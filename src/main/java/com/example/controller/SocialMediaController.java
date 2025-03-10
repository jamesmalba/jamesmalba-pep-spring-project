package com.example.controller;

import com.example.entity.*; 
import com.example.service.*; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Receives username and password and responds with json of account if successful.
     * Uses account service for account processing.
     *
     * @param account The request body containing the acount username and password.
     * @return ResponseEntity with json of the account if successful, 409 otherwise.
     */
    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        Account savedAccount = accountService.addAccount(account);
        if (savedAccount == null) {
            return ResponseEntity.status(409).body(null);
        }
        return ResponseEntity.status(200).body(savedAccount);
    }

    /**
     * Verifies username and password and responds with json of account with id if successful.
     * Uses account service for account processing.
     *
     * @param account The request body containing the acount username and password.
     * @return ResponseEntity with json of the account if successful, 401 otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<Account> verifyLogin(@RequestBody Account account) {
        Account verifiedAccount = accountService.verifyLogin(account);
        if (verifiedAccount == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.status(200).body(verifiedAccount);
    }

    /**
     * Posts a new message to the database. 
     * Uses message service for adding new message.
     *
     * @param message The request body containing the new message.
     * @return ResponseEntity with json of the message if successful, 400 otherwise.
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message savedMessage = messageService.createMessage(message);
        if (savedMessage == null) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(savedMessage);
    }

    /**
     * Gets all messages from the database. 
     * Uses message service to get all messages.
     *
     * @return ResponseEntity with list of messages in json.
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }
    
    /**
     * Gets message by message id from database. 
     * Uses message service to get message by id.
     *
     * @param messageId The message id used to find the message. 
     * @return ResponseEntity with json of the message. Empty if no message found.
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable int messageId) {
        return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
    }

    /**
     * Deletes message by message id from database. 
     * Uses message service to delete message by id.
     *
     * @param messageId The message id used to delete the message. 
     * @return ResponseEntity with json of the deleted message. Empty if no message found.
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        int rowsUpdated = messageService.deleteMessageById(messageId);
        if (rowsUpdated == 0) {
            return ResponseEntity.status(200).body(null);
        }
        else return ResponseEntity.status(200).body(rowsUpdated);
    }

    /**
     * Update message by message id and new message text from database. 
     * Uses message service to update message by id.
     *
     * @param messageId, messageText Message id used to update the message and messageText containing updated message. 
     * @return ResponseEntity with json of the deleted message. Empty if no message found.
     */
    @PatchMapping("/messages/{messageId}") 
    public ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Message message) {
        int rowsUpdated = messageService.updateMessageById(messageId, message.getMessageText());
        if (rowsUpdated == 0) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(rowsUpdated);
    }

    /**
     * Gets all messages by account id from database. 
     * Uses message service to get all messages by account id.
     *
     * @param accountId The account id used to get all the messages. 
     * @return ResponseEntity with json of a list of messages. Empty if no messages found.
     */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int accountId) {
        return ResponseEntity.status(200).body(messageService.getAllMessagesByAccountId(accountId));
    }
}
