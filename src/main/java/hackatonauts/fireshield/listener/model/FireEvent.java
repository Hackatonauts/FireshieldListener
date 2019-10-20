package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hackatonauts.fireshield.listener.helpers.Logger;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class FireEvent {

    ObjectMapper jsonMapper = new ObjectMapper();

    private String id;
    private String sourceId;
    private String title;
    private String description;
    private Geometries[] geometries;
    private int confidence = 100;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FireEvent(@JsonProperty("id") String sourceId, @JsonProperty("title") String title, @JsonProperty("description") String description, @JsonProperty("geometries") Geometries[] geometries) {
        this.sourceId = sourceId;
        this.title = title;
        this.description = description;
        this.geometries = geometries;
    }

    public FireEvent(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.logDebug(jsonNode.asText());

        this.sourceId = jsonNode.get("id").asText();
        this.title = jsonNode.get("title").asText();
        this.description = jsonNode.get("description").asText();;
        this.geometries[0] = new Geometries(jsonNode.get("geometries").asText());
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

    public Instant getDateInstant() {
        return Instant.parse(this.geometries[0].getDate());
    }

    public void setIdFromJsonString(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = jsonNode.get("id").asText();
    }
}
