package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.FireEvent;
import hackatonauts.fireshield.listener.model.FireEventSource;
import hackatonauts.fireshield.listener.model.FireModel;
import hackatonauts.fireshield.listener.model.FireResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
		FireService service = new FireService();
		FireModel model = service.getFireModel();
		FireEvent event = model.getEvents().get(0);
		FireResponse response = new FireResponse(event.getTitle(),
												event.getGeometries()[0].getPosition(),
												event.getGeometries()[0].getDate(),
												new FireEventSource("EONET", event.getSourceId()), event.getConfidence());
		System.out.println(response.toString());
	}
}
