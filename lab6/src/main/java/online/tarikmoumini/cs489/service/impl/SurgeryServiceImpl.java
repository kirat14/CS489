package online.tarikmoumini.cs489.service.impl;

import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.model.Surgery;
import online.tarikmoumini.cs489.repository.SurgeryRepository;
import online.tarikmoumini.cs489.service.SurgeryService;

import java.util.List;

@Service
public class SurgeryServiceImpl implements SurgeryService {

//    @Autowired
    private SurgeryRepository surgeryRepository;

//    @Autowired
    public SurgeryServiceImpl(SurgeryRepository surgeryRepository) {
        this.surgeryRepository = surgeryRepository;
    }

//    public SurgerieserviceImpl() {
//
//    }

    @Override
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public Surgery addNewSurgery(Surgery newSurgery) {
        return surgeryRepository.save(newSurgery);
    }

    @Override
    public Surgery getSurgeryId(Integer SurgeryId) {
        return surgeryRepository.findById(SurgeryId)
                .orElse(null);
    }

    @Override
    public Surgery updateSurgery(Surgery editedPublished) {
        return surgeryRepository.save(editedPublished);
    }

    @Override
    public void deleteSurgeryById(Integer SurgeryId) {
        surgeryRepository.deleteById(SurgeryId);
    }

    @Override
    public List<Surgery> getSurgeryByNameStart(String nameStart) {
        return surgeryRepository.findSurgeryByNameIsStartingWith(nameStart);
    }
}
