package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.helpers.TimeTravelManager;
import hackatonauts.fireshield.listener.model.FireEvent;
import hackatonauts.fireshield.listener.model.FireEventSource;
import hackatonauts.fireshield.listener.model.FireModel;
import hackatonauts.fireshield.listener.model.FireResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class FireEventsListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireEventsListenerApplication.class, args);
		TimeTravelManager timeManager = new TimeTravelManager("2019-10-10T01:00:00Z");
		FireService service = new FireService();
		ModisParser parser = new ModisParser();
		FireModel model = service.getFireModel(20);
		List<FireEvent> eventList = model.getEvents();
		List<FireResponse> modisEventsList = parser.getEvents();

		for (FireEvent e : eventList) {
			if (timeManager.getCurrentDateInstant().isAfter(e.getDateInstant())) {
				System.out.println(service.postFireResponse(new FireResponse(e)).toString());
			}
		}

		for (FireResponse e : modisEventsList) {
			if (timeManager.getCurrentDateInstant().isAfter(e.getDateInstant())) {
				System.out.println(service.postFireResponse(e).toString());

				try {
					Thread.sleep(10);
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		}




		for(Instant lastInstatnt = timeManager.getCurrentDateInstant();
			lastInstatnt.isBefore(Calendar.getInstance().toInstant());
			timeManager.increaseDate(Calendar.HOUR, 3)) {

//			model = service.getFireModel(1);
			List<FireEvent> currentEventList = model.getEvents();
			for (FireEvent e : eventList) {
				if (timeManager.getCurrentDateInstant().isAfter(e.getDateInstant()) &&
				lastInstatnt.isBefore(e.getDateInstant())) {
					System.out.println(service.postFireResponse(new FireResponse(e)).toString());
				}
			}

			for (FireResponse e : modisEventsList) {
				if (timeManager.getCurrentDateInstant().isAfter(e.getDateInstant()) &&
						lastInstatnt.isBefore(e.getDateInstant())) {
					System.out.println(service.postFireResponse(e).toString());
				}
			}

			lastInstatnt = timeManager.getCurrentDateInstant();

			try {
				Thread.sleep(10);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}
	}
