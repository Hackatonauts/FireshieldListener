package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.helpers.Constants;
import hackatonauts.fireshield.listener.helpers.Logger;
import hackatonauts.fireshield.listener.helpers.TimeTravelManager;
import hackatonauts.fireshield.listener.model.FireEvent;
import hackatonauts.fireshield.listener.model.FireModel;
import hackatonauts.fireshield.listener.model.FireResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
public class FireEventsListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireEventsListenerApplication.class, args);
        TimeTravelManager timeManager = new TimeTravelManager("2019-10-10T01:00:00Z");
        FireService service = new FireService();
        ModisParser parser = new ModisParser();

        Logger.logInfo("getting initial Fire Events from EONET");
        FireModel model = service.getFireModel();
        List<FireEvent>   eventList = model.getEvents();
        Logger.logInfo("getting initial Fire Events from MODIS");
        List<FireResponse>  modisEventsList = parser.getEvents();

        for (FireEvent fireEvent : eventList) {
            if (timeManager.getCurrentDateInstant().isAfter(fireEvent.getDateInstant())) {
                ResponseEntity<String> response = service.postFireResponse(new FireResponse(fireEvent));
                HttpStatus status = response.getStatusCode();
                if (!status.equals(HttpStatus.CREATED)) {
                    Logger.logError("Received status: " + status.toString());
                }

                fireEvent.setIdFromJsonString(response.getBody());
            }
        }

            for (FireResponse fireResponse : modisEventsList) {
                if (timeManager.getCurrentDateInstant().isAfter(fireResponse.getDateInstant())) {
                    ResponseEntity<String> response = service.postFireResponse(fireResponse);
                    HttpStatus status = response.getStatusCode();
                    if (!status.equals(HttpStatus.CREATED)) {
                        Logger.logError("Received status: " + status.toString());
                    }

                    fireResponse.setIdFromJsonString(response.getBody());
                }
            }

            try {
                Thread.sleep(Constants.sleepTime);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            for (Instant lastInstatnt = timeManager.getCurrentDateInstant();
                 lastInstatnt.isBefore(Calendar.getInstance().toInstant());
                 timeManager.increaseDate(Constants.timeIncreasingUnit, Constants.timeIncreasingValue)) {

                model = service.getFireModel(1);
                List<FireEvent> currentEventList = model.getEvents();
                for (FireEvent fireEvent : currentEventList) {
                    if (timeManager.getCurrentDateInstant().isAfter(fireEvent.getDateInstant()) &&
                            lastInstatnt.isBefore(fireEvent.getDateInstant())) {
                        ResponseEntity<String> response = service.postFireResponse(new FireResponse(fireEvent));
                        HttpStatus status = response.getStatusCode();
                        if (!status.equals(HttpStatus.CREATED)) {
                            Logger.logError("Received status: " + status.toString());
                        }

                        fireEvent.setIdFromJsonString(response.getBody());
                        eventList.add(fireEvent);
                    }
                }

                List<FireResponse> currentModisEventsList = parser.getEvents();
                for (FireResponse fireResponse : currentModisEventsList) {
                    if (timeManager.getCurrentDateInstant().isAfter(fireResponse.getDateInstant()) &&
                            lastInstatnt.isBefore(fireResponse.getDateInstant())) {
                        ResponseEntity<String> response = service.postFireResponse(fireResponse);
                        HttpStatus status = response.getStatusCode();
                        if (!status.equals(HttpStatus.CREATED)) {
                            Logger.logError("Received status: " + status.toString());
                        }

                        fireResponse.setIdFromJsonString(response.getBody());
                        modisEventsList.add(fireResponse);
                    }
                }

                for(FireEvent fireEvent : eventList) {
                    if(timeManager.getCurrentDateInstant().minus(5, ChronoUnit.DAYS).isAfter(fireEvent.getDateInstant())) {
                        HttpStatus status = service.putFireResponseClosed(fireEvent);
                        eventList.remove(fireEvent);
                    }
                }

                for(FireResponse fireResponse : modisEventsList) {
                    if(timeManager.getCurrentDateInstant().minus(5, ChronoUnit.DAYS).isAfter(fireResponse.getDateInstant())) {
                        HttpStatus status = service.putFireResponseClosed(fireResponse);
                        eventList.remove(fireResponse);
                    }

                }

                lastInstatnt = timeManager.getCurrentDateInstant();
                Logger.logInfo("Last checked date: " + lastInstatnt.toString());

                try {
                    Thread.sleep(Constants.sleepTime);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

            Logger.logInfo("Current date reached: " + timeManager.getCurrentDateInstant().toString());
        }
    }
