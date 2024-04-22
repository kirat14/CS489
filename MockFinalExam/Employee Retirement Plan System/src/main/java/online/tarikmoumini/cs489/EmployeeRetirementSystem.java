package online.tarikmoumini.cs489;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeRetirementSystem {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeRetirementSystem.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("Hello RESTful Web API");
            System.out.println("Employee Retirement System WebAPI server. Starting...");
            System.out.println(
                    "Employee Retirement System WebAPI server. Started.\nRunning Apache Tomcat service and Listening for HTTP Request on Port number, 8080");
            System.out.println("To see list of Publishers, curl");
            /* 
             * curl -X POST http://localhost:8080/ersweb/api/v1/employees/add \
             * -H 'Content-Type: application/json' \
             * -d '{"firstName": "Anna","lastName": "Smith","yearlySalary":
             * 150000.00,"referenceNumber": "SM1009","enrollmentDate":
             * "2023-08-16","retirementDate": "2026-09-29","monthlyContribution": null}'
             */
        };
    }
}
