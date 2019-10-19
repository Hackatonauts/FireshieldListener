package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Car;
import hackatonauts.fireshield.listener.model.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
    CommandLineRunner initDatabase(CarRepository carRepository, TenantRepository tenantRepository) {
		return args -> {
			logger.info("Preloading " + carRepository.save(new Car("Fiat", "125p")));
			logger.info("Preloading " + carRepository.save(new Car("BMW", "3")));

			logger.info("Preloading " + tenantRepository.save(new Tenant("Jan", "Janowski")));
			logger.info("Preloading " + tenantRepository.save(new Tenant("Pawel", "Pawlowski")));
		};
	}
}
