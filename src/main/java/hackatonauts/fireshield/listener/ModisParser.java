package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.FireEventSource;
import hackatonauts.fireshield.listener.model.FireResponse;
import hackatonauts.fireshield.listener.model.Position;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModisParser {

    public ModisParser() {

    }

    public List<FireResponse> getEvents() {
        FireService service = new FireService();
        String csvResponse =  service.getCSVEvents().getBody();
        List<FireResponse> events = new ArrayList<>();
        if (csvResponse != null) {
            try {
                CSVParser csvParser = CSVParser.parse(csvResponse, CSVFormat.DEFAULT.withFirstRecordAsHeader());

                for (CSVRecord csvRecord : csvParser) {
                    FireResponse csvEvent = new FireResponse("title",
                            new Position(new double[] {Double.valueOf(csvRecord.get("longitude")), Double.valueOf(csvRecord.get("latitude"))}),
                            csvRecord.get("acq_date"), new FireEventSource("MODIS", ""), Integer.valueOf(csvRecord.get("confidence")));
                    events.add(csvEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return events;
    }
}
