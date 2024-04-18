package online.tarikmoumini.cs489.service.impl;

import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.model.Address;
import online.tarikmoumini.cs489.repository.AddressRepository;
import online.tarikmoumini.cs489.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addNewAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }
}
