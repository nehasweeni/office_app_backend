package com.example.demo.data.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 ******* Describes booking table *******
 *  - booking_id is auto-incremented, and the primary key
 *  - booking_date represents date of booking
 *  - userID is the foreign key from user_reg table
 *  - officeID is the foreign key from office table
 */

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "booking_id", unique = true, nullable = false)
    private int booking_id;

    @Column(name = "booking_date", nullable = false)
    private Date booking_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officeID")
    private Office office;





}
