package hackatonauts.fireshield.listener.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireEventSource {
    private String type;
    private String id;

    public FireEventSource(String type, String id) {
        this.type = type;
        this.id = id;
    }
}
