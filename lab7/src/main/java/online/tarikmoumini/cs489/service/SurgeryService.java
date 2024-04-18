package online.tarikmoumini.cs489.service;

import java.util.List;

import online.tarikmoumini.cs489.model.Surgery;


public interface SurgeryService {

    List<Surgery> getAllSurgeries();
    Surgery addNewSurgery(Surgery newSurgery);

    Surgery getSurgeryId(Integer SurgeryId);

    Surgery updateSurgery(Surgery editedPublished);

    void deleteSurgeryById(Integer SurgeryId);

    List<Surgery> getSurgeryByNameStart(String nameStart);

}
