package online.tarikmoumini.cs489.repository;

import online.tarikmoumini.cs489.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
