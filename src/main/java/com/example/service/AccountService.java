package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Registers a user using account repository. 
     * Checks if username not blank, password is at least 4 characters, and account with username does not exist.
     *
     * @param account The account being added. 
     * @return Account The account object with the generated id if added. Null otherwise. 
     */
    public Account addAccount(Account account){
        if (account.getUsername().isEmpty()) return null;
        if (account.getPassword().length() < 4) return null;
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        if (optionalAccount.isPresent()) return null;
        return accountRepository.save(account);
    }

    /**
     * Verfies if there exists an account with username and password. 
     * Checks if username and password combo exists.
     *
     * @param account The account being verified. 
     * @return Account The account object with account id if the account was found. Null otherwise.  
     */
    public Account verifyLogin(Account account) {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        else {
            return null;
        }
    }
    
}
