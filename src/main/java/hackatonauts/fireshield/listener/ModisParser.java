package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.model.FireEventSource;
import hackatonauts.fireshield.listener.model.FireResponse;
import hackatonauts.fireshield.listener.model.Position;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.format.datetime.DateFormatter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModisParser {

    public ModisParser() {

    }

    private String csvToIsoDate(String date, String time) {
        String strDate = date + " " + time;
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HHmm");

        Date parsedDate = null;
        try {
            parsedDate = sdfInput.parse(strDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        String output = "";
        if (parsedDate != null) {
            output = Instant.ofEpochMilli(parsedDate.getTime()).toString();
        }

        return output;
    }

    public List<FireResponse> getEvents() {
        FireService service = new FireService();
        String csvResponse =  service.getCSVEvents().getBody();
        List<FireResponse> events = new ArrayList<>();
        if (csvResponse != null) {
            try {
                CSVParser csvParser = CSVParser.parse(csvResponse, CSVFormat.DEFAULT.withFirstRecordAsHeader());

                for (CSVRecord csvRecord : csvParser) {
                    int confidence = Integer.valueOf(csvRecord.get("confidence"));
                    if (confidence != 100) {
                        continue;
                    }
                    FireResponse csvEvent = new FireResponse("",
                            new Position(new double[] { Double.valueOf(csvRecord.get("longitude")),
                                    Double.valueOf(csvRecord.get("latitude")) }),
                            csvToIsoDate(csvRecord.get("acq_date"), csvRecord.get("acq_time")),
                            new FireEventSource("MODIS", ""),
                            confidence);
                    events.add(csvEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return events;
    }
}
