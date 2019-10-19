package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class FireResponse {

    private String name;
    private Position position;
    private String date;
    private FireEventSource source;
    private int confidence;

    public FireResponse(String name, Position position, String date, FireEventSource source, int confidence) {
        this.name = name;
        this.position = position;
        this.date = date;
        this.source = source;
        this.confidence = confidence;
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
