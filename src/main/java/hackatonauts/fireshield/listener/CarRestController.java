package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Position;
import hackatonauts.fireshield.listener.model.RentedCars;
import hackatonauts.fireshield.listener.model.Geometries;
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

    @GetMapping("/positions/{id}")
    Position oneCar(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @GetMapping("/positions")
    List<Position> allCars() {
        return carRepository.findAll();
    }

    @PostMapping("/positions")
    Position newCar(@RequestBody Position newPosition) {
        return carRepository.save(newPosition);
    }

    @PutMapping("/positions/{id}")
    Position replaceCar(@RequestBody Position newPosition, @PathVariable Long id) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrandName(newPosition.getBrandName());
                    car.setModel(newPosition.getModel());
                    return carRepository.save(car);
                })
                .orElseGet(() -> {
                    newPosition.setId(id);
                    return carRepository.save(newPosition);
                });
    }

    @DeleteMapping("/positions/{id}")
    void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    // tenant

    @PostMapping("/tenants")
    Geometries newTenant(@RequestBody Geometries newGeometries) {
        return tenantRepository.save(newGeometries);
    }

    @GetMapping("/tenants/{id}")
    Geometries oneTenant(@PathVariable Long id) {
        return tenantRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));   // change to tenant
    }

    @GetMapping("/tenants")
    List<Geometries> allTenants() {
        return tenantRepository.findAll();
    }

    @PutMapping("/tenants/{id}")
    Geometries replaceTenant(@RequestBody Geometries newGeometries, @PathVariable Long id) {
        return tenantRepository.findById(id)
                .map(geometries -> {
                    geometries.setDate(newGeometries.getDate());
                    geometries.setPosition(newGeometries.getPosition());
                    return tenantRepository.save(geometries);
                })
                .orElseGet(() -> {
                    newGeometries.setId(id);
                    return tenantRepository.save(newGeometries);
                });
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
