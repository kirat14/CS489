package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
