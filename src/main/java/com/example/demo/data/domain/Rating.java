package com.example.demo.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "rating_id", unique = true, nullable = false)
    private int rating_id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "comments", nullable = false)
    private String comments;

    @Column(name = "rating_date", nullable = false)
    private  Date rating_date;
}
