package com.moumini.tarik.restauranttablereservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moumini.tarik.restauranttablereservation.models.RestaurantTable;
import com.moumini.tarik.restauranttablereservation.services.TableService;

@RestController
@RequestMapping("/admin/")
@PreAuthorize("hasRole('ADMIN')")
public class ManagerController {

  @Autowired
  private TableService tableService; // Inject the TableService dependency

  @PostMapping("/tables/add")
  public ResponseEntity<RestaurantTable> addTable(@RequestBody RestaurantTable table) {
    RestaurantTable savedTable = tableService.createTable(table.getType(), table.getChairs());
    return ResponseEntity.ok(savedTable); // Return saved table object with status code 200 (OK)
  }

  @GetMapping("/tables/{id}")
  public ResponseEntity<RestaurantTable> getTableById(@PathVariable int id) {
    RestaurantTable table = tableService.getTableById(id);
    if (table != null) {
      return ResponseEntity.ok(table);
    } else {
      return ResponseEntity.notFound().build(); // Return 404 (Not Found) if table not found
    }
  }

  @PutMapping("/tables/{id}")
  public ResponseEntity<RestaurantTable> updateTable(@PathVariable int id, @RequestBody RestaurantTable table) {
    RestaurantTable updatedTable = tableService.updateTable(id, table.getType(), table.getChairs());
    if (updatedTable != null) {
      return ResponseEntity.ok(updatedTable);
    } else {
      return ResponseEntity.notFound().build(); // Return 404 (Not Found) if update fails
    }
  }

  @DeleteMapping("/tables/{id}")
  public ResponseEntity<Void> deleteTable(@PathVariable int id) {
    tableService.deleteTable(id);
    return ResponseEntity.noContent().build(); // Return 204 (No Content) on successful deletion
  }
}
