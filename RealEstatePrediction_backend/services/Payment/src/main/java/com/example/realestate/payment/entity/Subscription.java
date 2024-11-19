package com.example.realestate.payment.entity;

import com.example.realestate.User.UserRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Subscription {
    @Id
    private String id;

    @DBRef
    private Plan plan;

    @DBRef
    private UserRequest user;
}
