package com.moumini.tarik.restauranttablereservation.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moumini.tarik.restauranttablereservation.models.RestaurantTable;
import com.moumini.tarik.restauranttablereservation.models.TableType;
import com.moumini.tarik.restauranttablereservation.repositories.TableRepository;

@Service
public class TableService {

  @Autowired
  private TableRepository tableRepository;

  public RestaurantTable createTable(TableType type, int chairs) {
    RestaurantTable table = new RestaurantTable(type, chairs);
    return tableRepository.save(table);
  }

  public List<RestaurantTable> getAllTables() {
    return tableRepository.findAll();
  }

  public RestaurantTable getTableById(int id) {
    Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
    return optionalTable.orElse(null);
  }

  public RestaurantTable updateTable(int id, TableType type, int chairs) {
    RestaurantTable existingTable = getTableById(id);
    if (existingTable != null) {
      existingTable.setType(type);
      existingTable.setChairs(chairs);
      return tableRepository.save(existingTable);
    }
    return null;
  }

  public void deleteTable(int id) {
    tableRepository.deleteById(id);
  }
}
