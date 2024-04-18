package online.tarikmoumini.cs489.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import online.tarikmoumini.cs489.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    // Add custom query methods if needed
}

