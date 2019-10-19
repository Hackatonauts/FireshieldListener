package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Geometries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Geometries, Long> {
}
