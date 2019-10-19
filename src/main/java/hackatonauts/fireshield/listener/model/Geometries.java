package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Data
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class Geometries {

    private String date;
    private Position position;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Geometries(@JsonProperty("date") String date, @JsonProperty("coordinates") double[] position) {
        this.date = date;
        this.position = new Position(position);
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
