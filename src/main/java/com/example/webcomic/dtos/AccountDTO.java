package com.example.webcomic.dtos;

import com.example.webcomic.entities.Account;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String id;
    private String username;
    private String password;
    private Boolean isActive;
    private UserDTO user;
    private Integer role;
    private List<String> subscribeComicList;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.isActive = account.getIsActive();
        this.user = new UserDTO(account.getUser());
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
