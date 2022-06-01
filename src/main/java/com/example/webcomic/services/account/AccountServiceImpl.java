package com.example.webcomic.services.account;

import com.example.webcomic.dtos.AccountDTO;
import com.example.webcomic.dtos.UserDTO;
import com.example.webcomic.entities.Account;
import com.example.webcomic.response.ResponseObject;
import com.example.webcomic.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseObject checkLogin(AccountDTO accountDTO) {
        Account account = accountRepository.findAccountByUsername(accountDTO.getUsername())
                                .map(accountCheck -> {
                                    return accountCheck;
                                }).orElseGet(() -> {
                                    return null;
                                });
        if (Objects.isNull(account)) {
            return new ResponseObject("Fail", "Account invalid", "");
        }
        else if (!accountDTO.getPassword().equals(account.getPassword()))
        {
            return new ResponseObject("Fail", "Password invalid", "");
        } else if (!account.getIsActive()) {
            return new ResponseObject("Fail", "Account is banned", "");
        }
        return new ResponseObject("Success", "Logged in successfully", new AccountDTO(account));
    }

    @Override
    public ResponseObject createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findAccountByUsername(accountDTO.getUsername())
                                .map(accountCheck -> {
                                    return accountCheck;
                                }).orElseGet(() -> {
                                    return null;
                                });

        if (!Objects.isNull(account)) {
            return new ResponseObject("Fail", "Account already exists", "");
        }

        UserDTO user = new UserDTO();
        user.setName(accountDTO.getUsername());
        accountDTO.setUser(user);
        accountDTO.setIsActive(true);

        return new ResponseObject("Success", "Register successfully",
                new AccountDTO(accountRepository.save(new Account(accountDTO))));
    }

    @Override
    public ResponseObject banAccount(String id) {
        Account accountBanned = accountRepository.findAccountById(id)
                .map(account -> {
                    account.setIsActive(false);
                    return accountRepository.save(account);
                }).orElseGet(() -> {
                    return null;
                });
        if (Objects.isNull(accountBanned))
        {
            return new ResponseObject("Fail", "Ban failure", "");
        }
        return new ResponseObject("Success", "Ban successfully", new AccountDTO(accountBanned));
    }

    @Override
    public ResponseObject editAccount(AccountDTO accountDTO) {
        return new ResponseObject("Success", "Updated successfully", new AccountDTO(accountRepository.save(new Account(accountDTO))));
    }

    @Override
    public ResponseObject subComic(String id, String idComic) {
        Account account = accountRepository.findAccountById(id)
                .map(accountFound -> {
                    if (accountFound.getSubscribeComicList() != null)
                        accountFound.setSubscribeComicList(new ArrayList<>());
                    accountFound.getSubscribeComicList().add(idComic);
                    return accountRepository.save(accountFound);
                }).orElseGet(() -> {
                    return null;
                });
        return new ResponseObject("Success", "Subcribe successfully", new AccountDTO(account));
    }
}
