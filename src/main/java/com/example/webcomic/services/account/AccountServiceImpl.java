package com.example.webcomic.services.account;

import com.example.webcomic.dtos.AccountDTO;
import com.example.webcomic.dtos.UserDTO;
import com.example.webcomic.entities.Account;
import com.example.webcomic.entities.embedded.User;
import com.example.webcomic.response.ResponseObject;
import com.example.webcomic.repositories.AccountRepository;
import com.example.webcomic.utils.PasswordEncryptionSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseObject checkLogin(AccountDTO accountDTO) {
        Optional<Account> account = accountRepository.findAccountByUsername(accountDTO.getUsername());
        if (Objects.isNull(account)) {
            return new ResponseObject("Fail", "Account invalid", "");
        }
        else if (!PasswordEncryptionSingleton.getInstance().compare(accountDTO.getPassword(), account.get().getPassword()))
        {
            return new ResponseObject("Fail", "Password invalid", "");
        } else if (!account.get().getIsActive()) {
            return new ResponseObject("Fail", "Account is banned", "");
        }
        return new ResponseObject("Success", "Logged in successfully", new AccountDTO(account.get()));
    }

    @Override
    public ResponseObject createAccount(AccountDTO accountDTO) {
        Optional<Account> account = accountRepository.findAccountByUsername(accountDTO.getUsername());
        if (accountDTO.getUsername().isEmpty()) {
            return new ResponseObject("Fail", "Username is null", "");
        }
        else if (account.isPresent()) {
            return new ResponseObject("Fail", "Account already exists", "");
        }

        UserDTO user = new UserDTO();
        user.setName(accountDTO.getUsername());
        accountDTO.setUser(user);
        accountDTO.setIsActive(true);
        accountDTO.setPassword(PasswordEncryptionSingleton.getInstance().encrypt(accountDTO.getPassword()));

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
        return new ResponseObject("Success", "Ban successfully", new AccountDTO(accountBanned));
    }

    @Override
    public ResponseObject editAccount(String id, UserDTO userDTO) {
        Optional<Account> account = accountRepository.findAccountById(id);
        if (account.get().getUser().equals(userDTO)) {
            return new ResponseObject("Fail", "Changed information coincides with previous information", "");
        }
        account.get().setUser(new User(userDTO));
        return new ResponseObject("Success", "Updated successfully", new AccountDTO(accountRepository.save(account.get())));
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

    @Override
    public ResponseObject getAllAccount() {
        List<Account> listAccount = accountRepository.findAll();
        List<AccountDTO> listAccountDTO = new ArrayList<>();
        listAccount.forEach(account -> {
            listAccountDTO.add(new AccountDTO(account));
        });

        return new ResponseObject("Success", "Subcribe successfully", listAccountDTO);
    }

    @Override
    public ResponseObject changePassword(String idUser, String oldPassword, String newPassword) {
        Optional<Account> account = accountRepository.findAccountById(idUser);
        if (PasswordEncryptionSingleton.getInstance().compare(oldPassword, account.get().getPassword())) {
            return new ResponseObject("Fail", "Wrong password", "");
        }
        account.get().setPassword(PasswordEncryptionSingleton.getInstance().encrypt(newPassword));
        return new ResponseObject("Success", "Change password successfully", new AccountDTO(accountRepository.save(account.get())));
    }
}
