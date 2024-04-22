package online.tarikmoumini.cs489.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.model.RetirementPlan;
import online.tarikmoumini.cs489.repository.RetirementPlanRepository;


@Service
public class RetirementServiceImpl {
    private RetirementPlanRepository retirementPlanRepository;

    @Autowired
    public RetirementServiceImpl(RetirementPlanRepository retirementPlanRepository) {
        this.retirementPlanRepository = retirementPlanRepository;
    }

    public RetirementPlan addNewRetirementPlan(RetirementPlan retirementPlanRequest) {

        return this.retirementPlanRepository.save(retirementPlanRequest);
        
    }
}

