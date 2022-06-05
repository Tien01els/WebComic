package com.example.webcomic.services.account;

import com.example.webcomic.dtos.AccountDTO;
import com.example.webcomic.dtos.UserDTO;
import com.example.webcomic.response.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    ResponseObject checkLogin(AccountDTO accountDTO);
    ResponseObject createAccount(AccountDTO accountDTO);
    ResponseObject banAccount(String idAccount);
    ResponseObject subComic(String idAccount, String idComic);
    ResponseObject getAllAccount();
    ResponseObject changePassword(String idAccount, String oldPassword, String newPassword);
    ResponseObject editUser(String idAccount, UserDTO userDTO);
    ResponseObject getAccount(String idAccount);
}
