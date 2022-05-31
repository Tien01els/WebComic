package com.example.webcomic.entities.embedded;

import com.example.webcomic.dtos.UserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String fullname;
    private int age;
    private String gender;
    private String email;

    public User(UserDTO user) {
        this.fullname = user.getName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }

}
