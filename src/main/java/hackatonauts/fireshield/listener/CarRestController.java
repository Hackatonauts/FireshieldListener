package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Car;
import hackatonauts.fireshield.listener.model.RentedCars;
import hackatonauts.fireshield.listener.model.Tenant;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
class CarRestController {

    private final TenantRepository tenantRepository;
    private final CarRepository carRepository;
    private final RentedCarsRepository rentedCarsRepository;

    CarRestController(TenantRepository tenantRepository, CarRepository carRepository, RentedCarsRepository rentedCarsRepository) {
        this.tenantRepository = tenantRepository;
        this.carRepository = carRepository;
        this.rentedCarsRepository = rentedCarsRepository;
    }

    @GetMapping("/cars/{id}")
    Car oneCar(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @GetMapping("/cars")
    List<Car> allCars() {
        return carRepository.findAll();
    }

    @PostMapping("/cars")
    Car newCar(@RequestBody Car newCar) {
        return carRepository.save(newCar);
    }

    @DeleteMapping("/cars/{id}")
    void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    // tenant

    @PostMapping("/tenants")
    Tenant newTenant(@RequestBody Tenant newTenant) {
        return tenantRepository.save(newTenant);
    }

    @GetMapping("/tenants/{id}")
    Tenant oneTenant(@PathVariable Long id) {
        return tenantRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));   // change to tenant
    }

    @GetMapping("/tenants")
    List<Tenant> allTenants() {
        return tenantRepository.findAll();
    }

    // rentedCars

    @PostMapping("/rentedcars")
    RentedCars newRentedCar(@RequestBody RentedCars newRentedCar) {
        return rentedCarsRepository.save(newRentedCar);
    }

    @GetMapping("/rentedcars/{id}")
    RentedCars oneRentedCar(@PathVariable Long id) {

        return rentedCarsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));   // change to rentedCars
    }

    @GetMapping("/rentedcars")
    List<RentedCars> allRentedCars() {
        return rentedCarsRepository.findAll();
    }

    @DeleteMapping("/rentedcars/{id}")
    void deleteRentedcar(@PathVariable Long id) {
        rentedCarsRepository.deleteById(id);
    }
}
