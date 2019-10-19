package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
