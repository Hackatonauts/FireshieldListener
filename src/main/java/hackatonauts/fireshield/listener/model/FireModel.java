package hackatonauts.fireshield.listener.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
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

}
