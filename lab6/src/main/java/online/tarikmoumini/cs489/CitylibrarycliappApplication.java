package online.tarikmoumini.cs489;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import online.tarikmoumini.cs489.model.Address;
import online.tarikmoumini.cs489.model.Appointment;
import online.tarikmoumini.cs489.model.Dentist;
import online.tarikmoumini.cs489.model.Manager;
import online.tarikmoumini.cs489.model.Patient;
import online.tarikmoumini.cs489.model.Surgery;
import online.tarikmoumini.cs489.service.AddressService;
import online.tarikmoumini.cs489.service.AppointmentService;
import online.tarikmoumini.cs489.service.SurgeryService;
import online.tarikmoumini.cs489.service.UserService;

@SpringBootApplication
public class CitylibrarycliappApplication implements CommandLineRunner {

    private final SurgeryService surgerie_service;
    private final AddressService addressService;
    private final AppointmentService appointmentService;
    private final UserService userService;

    public CitylibrarycliappApplication(SurgeryService surgerie_service,
            AddressService addressService, AppointmentService appointmentService, UserService userService) {
        this.surgerie_service = surgerie_service;
        this.addressService = addressService;
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CitylibrarycliappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Create the address
        var dentist_address = addressService
                .addNewAddress(new Address("1000 N 4th Street", "Fairfield", "IA", "52556"));

        var patient_address = addressService
                .addNewAddress(new Address("500 Main Street", "Springfield", "IL", "62701"));
        var manager_address = addressService
                .addNewAddress(new Address("123 Oak Avenue", "San Francisco", "CA", "94102"));

        var surgery = surgerie_service
                .addNewSurgery(new Surgery("Orthopedic Surgery", "123-456-7890", dentist_address));
        System.out.println("Surgery added");
        System.out.println(surgery);

        // Add Dentist, Patient, and Manager using UserService
        Dentist dentist = userService.addDentist(new Dentist("dentist_1", "dentist_pass", "John", "Dentist",
                "john.dentist@gmail.com", dentist_address, "Specialization1"));
        Patient patient = userService.addPatient(new Patient("patient_1", "patient_pass", "John", "Patient",
                "john.patient@gmail.com", patient_address, LocalDate.of(1990, 5, 15)));
        Manager manager = userService.addManager(
                new Manager("manager_1", "manager_pass", "John", "Manager", "john.manager@gmail.com", manager_address));

        // Create an Appointment
        Appointment appointment = new Appointment(1, LocalDateTime.now(), false, surgery, patient, dentist, manager);

        // Add Appointment using AppointmentService
        Appointment createdAppointment = appointmentService.addNewAppointment(appointment);

        System.out.println(createdAppointment);

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

    private Surgery getSurgeryById(Integer publisherId) {
        return surgerie_service.getSurgeryId(publisherId);
    }

    private void printAllSurgeries() {
        surgerie_service.getAllSurgeries()
                .forEach(System.out::println);
    }

    /*
     * private Surgery updateSurgeryById(Integer surgeryId) {
     * var surgeryFound = getSurgeryById(surgeryId);
     * if (surgeryFound != null) {
     * var address = new Address(null, "12 14th Street", "New York", "NY", "10927",
     * null, null);
     * surgeryFound.setAddress(address);
     * return surgerie_service.updateSurgery(surgeryFound);
     * } else {
     * return null;
     * }
     * }
     */
}
