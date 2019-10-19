package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Car;
import hackatonauts.fireshield.listener.model.RentedCars;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Getter
@Setter
@NoArgsConstructor
public class CarService {

    private List<Car> cars;
    private List<RentedCars> rentedCars;
    private RestTemplate restTemplate = new RestTemplate();

    private List<Car> getAllFireEvents() {
        ResponseEntity<List<Car>> response = restTemplate.exchange(
                "http://localhost:8082/cars/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Car>>() {
                });
        return cars = response.getBody();     //check it
    }

    public void carRental(Long tenantId, Long carId) {
        if (getAllCars().stream().anyMatch(car -> car.getId().equals(carId))) {
            if (!getAllRentedCars().stream().anyMatch(rented -> rented.getCarId().equals(carId))) {
                rentACar(tenantId, carId);
            } else {
                throw new MissingResourceException(
                        "The car is already rented",
                        this.getClass().getName(),
                        carId.toString()
                );
            }
        } else {
            throw new MissingResourceException(
                    "Car is not available in Car Rental",
                    this.getClass().getName(),
                    carId.toString()
            );
        }
    }
}
