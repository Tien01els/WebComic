package com.example.webcomic.repositories;

import com.example.webcomic.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{'username' : ?0}")
    Optional<Account> findAccountByUsername(String username);

    @Query("{'Id' : ?0}")
    Optional<Account> findAccountById(String id);

}
