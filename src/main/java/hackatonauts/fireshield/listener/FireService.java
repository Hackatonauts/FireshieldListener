package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.helpers.Constants;
import hackatonauts.fireshield.listener.model.FireModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

@Service
@Getter
@Setter
@NoArgsConstructor
public class FireService {

    private FireModel fireModel;
    private RestTemplate restTemplate = new RestTemplate();

    FireModel getFireModel() {
        ResponseEntity<FireModel> response = restTemplate.exchange(
                Constants.fireEvents, HttpMethod.GET, null,
                new ParameterizedTypeReference<FireModel>() {
                });

        return fireModel = response.getBody();
    }

    ResponseEntity<String> getCSVEvents() {
        return restTemplate.exchange(Constants.csvGlobal, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
        });
    }
}
