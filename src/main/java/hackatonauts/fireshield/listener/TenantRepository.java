package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
