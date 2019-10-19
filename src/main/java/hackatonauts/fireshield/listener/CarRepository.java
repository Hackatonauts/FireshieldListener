package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Position, Long> {
}
