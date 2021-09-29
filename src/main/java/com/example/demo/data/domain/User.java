package com.example.demo.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 ******* Describes user_reg table *******
 *  - id is auto-incremented, and the primary key
 *  - name represents first name and last name of user
 *  - email is the email of user
 *  - password is the password of the user
 *  - List bookingList allows the mapping between booking table and user_reg table
 */
@Entity
@Table(name = "user_reg")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList = new ArrayList<>();

}


