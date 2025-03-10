package com.example.controller;

import com.example.entity.*; 
import com.example.service.*; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
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

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        Account savedAccount = accountService.addAccount(account);
        if (savedAccount == null) {
            return ResponseEntity.status(409).body(null);
        }
        return ResponseEntity.status(200).body(savedAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> verifyLogin(@RequestBody Account account) {
        Account verifiedAccount = accountService.verifyLogin(account);
        if (verifiedAccount == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.status(200).body(verifiedAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message savedMessage = messageService.createMessage(message);
        if (savedMessage == null) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(savedMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }
    
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable int messageId) {
        return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        int rowsUpdated = messageService.deleteMessageById(messageId);
        if (rowsUpdated == 0) {
            return ResponseEntity.status(200).body(null);
        }
        else return ResponseEntity.status(200).body(rowsUpdated);
    }

    @PatchMapping("/messages/{messageId}") 
    public ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Message message) {
        int rowsUpdated = messageService.updateMessageById(messageId, message.getMessageText());
        if (rowsUpdated == 0) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(rowsUpdated);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int accountId) {
        return ResponseEntity.status(200).body(messageService.getAllMessagesByAccountId(accountId));
    }
}
