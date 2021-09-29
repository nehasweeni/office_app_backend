package com.example.demo.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 ******* Describes office table *******
 *  - office_id is auto-incremented, and the primary key
 *  - office_name represents name of the office
 *  - office_address is the location of the office
 *  - List bookings allows the mapping between booking table and office table
 */

@Entity
@Table(name = "office")
@Data
@NoArgsConstructor
public class Office {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "office_id", unique = true, nullable = false)
    private int office_id;

    @Column(name = "office_name", nullable = false)
    private String office_name;

    @Column(name = "office_address", nullable = false)
    private String office_address;

    @OneToMany(mappedBy = "office")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "office2")
    private List<Photos> photos = new ArrayList<>();


}
