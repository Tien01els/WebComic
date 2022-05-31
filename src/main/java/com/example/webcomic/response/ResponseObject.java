package com.example.webcomic.response;

import com.example.webcomic.dtos.AccountDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
