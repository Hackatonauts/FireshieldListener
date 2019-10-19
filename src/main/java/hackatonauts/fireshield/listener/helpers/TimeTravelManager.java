package hackatonauts.fireshield.listener.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class TimeTravelManager {
    Calendar calendar = Calendar.getInstance();

    public TimeTravelManager(String startDate) {
        Instant instant = Instant.parse(startDate);
        calendar.setTimeInMillis(instant.toEpochMilli());
    }

    public Instant getCurrentDateInstant() {
        return calendar.toInstant();
    }

    public void increaseDate(int field, int amount) {
        calendar.add(field, amount);
    }
}
