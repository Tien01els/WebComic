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

    @PutMapping("/editAccount/{idUser}")
    public ResponseEntity<ResponseObject> editAccount(@PathVariable String idUser, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(accountService.editAccount(idUser, userDTO));
    }

    @PutMapping("/changePassword/{idUser}")
    public ResponseEntity<ResponseObject> changePassword(@PathVariable String idUser, @RequestBody Map<String, String> password) {
        return ResponseEntity.ok(accountService.changePassword(idUser, password.get("oldPassword"), password.get("newPassword")));
    }

    @GetMapping("/subComic/{id}/{idComic}")
    public ResponseEntity<ResponseObject> searchComics(@PathVariable String id, @PathVariable String idComic) {
        return ResponseEntity.ok(accountService.subComic(id, idComic));
    }

}
