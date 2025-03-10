package com.example.controller;

import com.example.entity.*; 
import com.example.service.*; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RestController;

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

    public SocialMediaController(AccountService accountService) {
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
        Account savedAccount = accountService.verifyLogin(account);
        if (savedAccount == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.status(200).body(savedAccount);
    }
}
