package com.example.webcomic.services.account;

import com.example.webcomic.dtos.AccountDTO;
import com.example.webcomic.dtos.UserDTO;
import com.example.webcomic.response.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    ResponseObject checkLogin(AccountDTO accountDTO);
    ResponseObject createAccount(AccountDTO accountDTO);
    ResponseObject banAccount(String id);
    ResponseObject subComic(String id, String idComic);
    ResponseObject getAllAccount();
    ResponseObject changePassword(String idUser, String oldPassword, String newPassword);
    ResponseObject editAccount(String idUser, UserDTO userDTO);
}
