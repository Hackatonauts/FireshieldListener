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
import javax.persistence.Id;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class FireModel {

    private List<FireEvent> events;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FireModel(@JsonProperty("events") List<FireEvent> events) {
        this.events = events;
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
