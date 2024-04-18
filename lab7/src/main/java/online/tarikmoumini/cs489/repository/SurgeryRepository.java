package online.tarikmoumini.cs489.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import online.tarikmoumini.cs489.model.Surgery;

import java.util.List;
import java.util.Optional;

//@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Integer> {

    @Query(value = "select s from Surgery s")
    public List<Surgery> getMyCustomListOfSurgery();

    @Query(value = "select s from Surgery s where s.name = :name")
    public Optional<Surgery> getSurgeryByName(String name);

   /*  @Query(value = "SELECT * FROM `cs489-apsd-citylibrary-db2`.publishers p where p.name like 'Ap%'", nativeQuery = true)
    public Optional<Surgery> getMyNativeCustomPublisherByName(String name); */

    // Using Query methods
    public List<Surgery> findSurgeryByNameIsStartingWith(String strNameStart);
}
