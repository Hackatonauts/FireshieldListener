package hackatonauts.fireshield.listener.model;


import com.fasterxml.jackson.annotation.JsonCreator;
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
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FireModel {

    ObjectMapper jsonMapper = new ObjectMapper();

    private List<FireEvent> events;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FireModel(@JsonProperty("events") List<FireEvent> events) {
        this.events = events;
    }

    public FireModel(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        events = new ArrayList<>();

        Logger.logDebug(jsonNode.asText());


        for( JsonNode node : jsonNode.get("events")) {
            events.add(new FireEvent(node.asText()));
        }
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

    public List<FireEvent> getEvents() {
        return events;
    }
}
