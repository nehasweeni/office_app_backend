package com.example.demo.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "photos")
@Data
@NoArgsConstructor
public class Photos {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "photos_id", unique = true, nullable = false)
    private int photos_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office2;

    @Lob
    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @Column(name = "content_type", nullable = false)
    private String content_type;

}
