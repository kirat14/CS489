package online.tarikmoumini.cs489;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CitylibrarycliappApplication {

    

    public static void main(String[] args) {
        SpringApplication.run(CitylibrarycliappApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("Hello RESTful Web API");
            System.out.println("CityLibrary WebAPI server. Starting...");
            System.out.println("CityLibrary WebAPI server. Started.\nRunning Apache Tomcat service and Listening for HTTP Request on Port number, 8080");
            System.out.println("To see list of Publishers, curl http://localhost:8080/adsweb/api/v1/patient/list");
        };
    }
}
