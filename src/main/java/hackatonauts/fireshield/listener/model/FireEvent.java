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
public class FireEvent {

    private String sourceId;
    private String title;
    private String description;
    private Geometries geometries;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FireEvent(@JsonProperty("id") String sourceId, @JsonProperty("title") String title, @JsonProperty("description") String description, @JsonProperty("geometries") Geometries geometries) {
        this.sourceId = sourceId;
        this.title = title;
        this.description = description;
        this.geometries = geometries;
    }

    @Override
    public String toString() {
        return "";
    }
}
