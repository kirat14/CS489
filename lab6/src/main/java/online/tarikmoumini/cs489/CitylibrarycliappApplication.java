package online.tarikmoumini.cs489;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import online.tarikmoumini.cs489.model.Address;
import online.tarikmoumini.cs489.model.Surgery;
import online.tarikmoumini.cs489.service.AddressService;
import online.tarikmoumini.cs489.service.SurgeryService;

@SpringBootApplication
public class CitylibrarycliappApplication implements CommandLineRunner {

    private final SurgeryService surgerie_service;
    private final AddressService addressService;

    public CitylibrarycliappApplication(SurgeryService surgerie_service,
            AddressService addressService) {
        this.surgerie_service = surgerie_service;
        this.addressService = addressService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CitylibrarycliappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Data Persistence using Spring Data JPA");
        // Create new Surgery with existing Address
        System.out.println("Creating new Surgery with existing Address");

        // Create the address
        var created_address = addressService
                .addNewAddress(new Address(null, "1000 N 4th Street", "Fairfield", "IA", "52556", null, null));

        System.out.println(created_address);

        var surgery_instance = new Surgery(null, "Orthopedic Surgery", "123-456-7890", created_address);
        var created_surgery = surgerie_service.addNewSurgery(surgery_instance);
        System.out.println("Surgery added");
        System.out.println(created_surgery);

        /*
         * System.out.println("Creating new Surgery with new Address");
         * var address = new Address(null, "20 South Court", "Los Angeles", "CA",
         * "90210-0002", created_surgery, null);
         * var savedSurgery = addNewSurgeryAndAddress("Dental Surgery", "987-654-3210",
         * address);
         * System.out.println(savedSurgery);
         */
        // printAllSurgeries();

        /*
         * var surgery_id = 1;
         * var found_surgery = getSurgeryById(surgery_id);
         * if(found_surgery != null) {
         * System.out.printf("Surgery with id: %d, found\n %s", surgery_id,
         * found_surgery);
         * } else {
         * System.out.printf("Error: Surgery with id: %d, is not found\n", surgery_id);
         * }
         * 
         * var updated_surgery = updateSurgeryById(surgery_id);
         * if(updated_surgery != null) {
         * System.out.printf("Surgery with id: %d, updated\n %s", surgery_id,
         * updated_surgery);
         * } else {
         * System.out.printf("Error: Surgery with id: %d, is not found\n", surgery_id);
         * }
         * // Delete publisher by id
         * this.surgerie_service.deleteSurgeryById(1);
         * 
         * System.out.println("Surgery that start with Or : " +
         * surgerie_service.getSurgeryByNameStart("Or"));
         */
    }

    private Surgery addNewSurgeryAndAddress(String name, String phone_number, Address address) {
        var surgery_instance = new Surgery(null, name, phone_number, address);
        return surgerie_service.addNewSurgery(surgery_instance);
    }

    private Surgery getSurgeryById(Integer publisherId) {
        return surgerie_service.getSurgeryId(publisherId);
    }

    private void printAllSurgeries() {
        surgerie_service.getAllSurgeries()
                .forEach(System.out::println);
    }

    private Surgery updateSurgeryById(Integer surgeryId) {
        var surgeryFound = getSurgeryById(surgeryId);
        if (surgeryFound != null) {
            var address = new Address(null, "12 14th Street", "New York", "NY", "10927", null, null);
            surgeryFound.setAddress(address);
            return surgerie_service.updateSurgery(surgeryFound);
        } else {
            return null;
        }
    }
}
