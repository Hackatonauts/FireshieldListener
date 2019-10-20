package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.IOException;

@Data
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class Geometries {
    ObjectMapper jsonMapper = new ObjectMapper();

    private String date;
    private Position position;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Geometries(@JsonProperty("date") String date, @JsonProperty("coordinates") double[] position) {
        this.date = date;
        this.position = new Position(position);
    }

    public Geometries(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.date = jsonNode.get("date").asText();
        double[] tab = {jsonNode.get("coordinates").get(0).asDouble(), jsonNode.get("coordinates").get(1).asDouble()};
        this.position = new Position(tab);
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
