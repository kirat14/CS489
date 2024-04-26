package com.moumini.tarik.restauranttablereservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moumini.tarik.restauranttablereservation.models.RestaurantTable;

public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {
}
