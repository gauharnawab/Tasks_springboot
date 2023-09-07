package com.country.entity;

import jakarta.persistence.*;


@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state_name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    

    // Getter and Setter methods
}

