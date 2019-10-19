package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.FireEvent;
import hackatonauts.fireshield.listener.model.FireEventSource;
import hackatonauts.fireshield.listener.model.FireModel;
import hackatonauts.fireshield.listener.model.FireResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FireEventsListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireEventsListenerApplication.class, args);
		FireService service = new FireService();
		FireModel model = service.getFireModel(10);
		List<FireEvent> eventList = model.getEvents();
		eventList.stream().forEach(e -> System.out.println(e.getGeometries()[0].getDate()));
		FireResponse response = new FireResponse(eventList.get(0).getTitle(),
                eventList.get(0).getGeometries()[0].getPosition(),
                eventList.get(0).getGeometries()[0].getDate(),
												new FireEventSource("EONET", eventList.get(0).getSourceId()), eventList.get(0).getConfidence());
		System.out.println(response.toString());
    }
}
