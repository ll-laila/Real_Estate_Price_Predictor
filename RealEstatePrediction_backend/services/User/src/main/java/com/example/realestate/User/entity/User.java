package com.example.realestate.User.entity;

<<<<<<< HEAD

import jakarta.persistence.*;
import lombok.*;
=======
import lombok.*;
import org.springframework.data.annotation.Id;
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class User {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

=======
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334
}
