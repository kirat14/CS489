package online.tarikmoumini.cs489.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import online.tarikmoumini.cs489.dto.AddressDTO;
import online.tarikmoumini.cs489.model.Address;
import online.tarikmoumini.cs489.repository.AddressRepository;
import online.tarikmoumini.cs489.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addNewAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }

    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll(Sort.by("city").ascending())
                .stream()
                // .sorted(Comparator.comparing(Patient::getName))
                .map(address -> new AddressDTO(
                                address.getStreet(),
                                address.getCity(),
                                address.getState(),
                                address.getZip_code(),
                                address.getUser().getFirstname()))
                .toList();
    }
}
