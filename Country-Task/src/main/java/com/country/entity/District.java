package com.country.entity;

import jakarta.persistence.*;


@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String district_name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;



    // Getter and Setter methods
}

