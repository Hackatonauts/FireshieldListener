package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.RentedCars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentedCarsRepository extends JpaRepository<RentedCars, Long> {
}
