package com.moumini.tarik.restauranttablereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantTableReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantTableReservationApplication.class, args);
		System.out.println("localhost:8080/admin/customers");
		System.out.println("localhost:8080/admin/tables");
		System.out.println("localhost:8080/booking/book");
	}

}
