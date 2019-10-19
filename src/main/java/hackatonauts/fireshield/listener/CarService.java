package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Position;
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

    private List<Position> positions;
    private List<RentedCars> rentedCars;
    private RestTemplate restTemplate = new RestTemplate();

    private List<Position> getAllCars() {
        ResponseEntity<List<Position>> response = restTemplate.exchange(
                "http://localhost:8082/positions/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Position>>(){});
        return positions = response.getBody();     //check it
    }

    private List<RentedCars> getAllRentedCars() {
        ResponseEntity<List<RentedCars>> response = restTemplate.exchange(
                "http://localhost:8082/rentedcars/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RentedCars>>(){});
        return rentedCars = response.getBody();     //check it
    }

    private void rentACar(Long tenantId, Long carId) {
        RentedCars rentedCar = new RentedCars();
        rentedCar.setTenantId(tenantId);
        rentedCar.setCarId(carId);
        restTemplate.postForEntity("http://localhost:8082/rentedcars/", rentedCar, RentedCars.class);
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
                    "Position is not available in Position Rental",
                    this.getClass().getName(),
                    carId.toString()
            );
        }
    }
}
