package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Objects;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class Position {
    private double[] coordinates;

    private double lat;
    private double lng;

    public Position(double[] coordinates) {
        this.coordinates = coordinates;
        this.lng=coordinates[0];
        this.lat=coordinates[1];
    }

    @Override
    public String toString() {
        return "position\": [ {" +
                "lat \": " + lat + "," +
                "lng\": " + lng +
                "] }";
    }
}
