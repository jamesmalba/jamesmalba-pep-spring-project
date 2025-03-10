package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for handling database requests based on account.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Finds an account based on the username.
     *
     * @param username The username being searched.
     * @return Optional account if account is found.
     */
    Optional<Account> findByUsername(String username);

    /**
     * Finds an account based on username and password.
     *
     * @param username, password The login info being searched for. 
     * @return The account object if found.
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);

    /**
     * Finds an account based on account id.
     *
     * @param accountId The account id to be searched.
     * @return The accoount object if found.
     */
    Optional<Account> findByAccountId(int accountId);
}
