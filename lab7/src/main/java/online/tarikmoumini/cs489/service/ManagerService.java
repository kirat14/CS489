package online.tarikmoumini.cs489.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.tarikmoumini.cs489.model.Manager;
import online.tarikmoumini.cs489.repository.ManagerRepository;

@Service
public class ManagerService {

    private ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager addNewManager(Manager newManager) {
        return managerRepository.save(newManager);
    }

    // Add more service methods as needed
}

