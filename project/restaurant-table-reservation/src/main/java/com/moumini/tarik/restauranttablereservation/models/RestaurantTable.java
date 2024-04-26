package com.moumini.tarik.restauranttablereservation.models;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TableType type;

    private int chairs;

    public RestaurantTable() {
    }

    public RestaurantTable(TableType type, int chairs) {
        this.type = type;
        this.chairs = chairs;
    }
}

