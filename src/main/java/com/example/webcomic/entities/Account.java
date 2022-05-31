package com.example.webcomic.entities;

import com.example.webcomic.dtos.AccountDTO;
import com.example.webcomic.entities.embedded.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Account")
public class Account {
    @Id
    private String id;
    private String username;
    private String password;
    private Boolean isActive;
    private User user;
    private Integer role;
    private List<String> subscribeComicList;

    public Account(AccountDTO account) {
        this.id = account.getId();
        this.username = account.getUserName();
        this.password = account.getPassword();
        this.isActive = account.getIsActive();
        this.user = new User(account.getUser());
        this.role = account.getRole();
        if(account.getSubscribeComicList() != null)
            account.getSubscribeComicList().forEach(subscribeComic -> {
                if (this.subscribeComicList == null) {
                    this.subscribeComicList = new ArrayList<>();
                }
                this.subscribeComicList.add(subscribeComic);
            });
    }
}
