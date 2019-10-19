package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class Geometries {

    private String date;
    private Position position;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Geometries(@JsonProperty("date") String date, @JsonProperty("position") double[] position) {
        this.date = date;
        this.position = new Position(position);
    }

    @Override
    public String toString() {
        return "geometries\": [" +
                "{" +
                "date\": \"" + date + "\"," +
                position.toString() +
                "] }";
    }
}
