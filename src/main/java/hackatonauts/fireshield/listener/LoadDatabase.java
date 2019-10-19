package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.Position;
import hackatonauts.fireshield.listener.model.Geometries;
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
			logger.info("Preloading " + carRepository.save(new Position(new double[]{12.3,45.6})));
			logger.info("Preloading " + carRepository.save(new Position(new double[]{99.9,000})));

			logger.info("Preloading " + tenantRepository.save(new Geometries("Jan", "Janowski")));
			logger.info("Preloading " + tenantRepository.save(new Geometries("Pawel", "Pawlowski")));
		};
	}
}
