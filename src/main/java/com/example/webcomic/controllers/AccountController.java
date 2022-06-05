package com.example.webcomic.controllers;

import com.example.webcomic.dtos.AccountDTO;
import com.example.webcomic.dtos.UserDTO;
import com.example.webcomic.response.ResponseObject;
import com.example.webcomic.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/app/1.0/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllAccount() {
        return ResponseEntity.ok(accountService.getAllAccount());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.checkLogin(accountDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @PutMapping("/banAccount/{idUser}")
    public ResponseEntity<ResponseObject> banAccount(@PathVariable String idUser) {
        return ResponseEntity.ok(accountService.banAccount(idUser));
    }

    @PostMapping("/provideAccount")
    public ResponseEntity<ResponseObject> provideAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @PutMapping("/editAccount/{idAccount}")
    public ResponseEntity<ResponseObject> editUser(@PathVariable String idAccount, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(accountService.editUser(idAccount, userDTO));
    }

    @PutMapping("/changePassword/{idAccount}")
    public ResponseEntity<ResponseObject> changePassword(@PathVariable String idAccount, @RequestBody Map<String, String> password) {
        return ResponseEntity.ok(accountService.changePassword(idAccount, password.get("oldPassword"), password.get("newPassword")));
    }

    @GetMapping("/subComic/{idAccount}/{idComic}")
    public ResponseEntity<ResponseObject> searchComics(@PathVariable String idAccount, @PathVariable String idComic) {
        return ResponseEntity.ok(accountService.subComic(idAccount, idComic));
    }

    @GetMapping("/getAccount/{idAccount}")
    public ResponseEntity<ResponseObject> getAccount(@PathVariable String idAccount) {
        return ResponseEntity.ok(accountService.getAccount(idAccount));
    }

}
