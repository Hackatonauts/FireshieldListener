package hackatonauts.fireshield.listener;

import com.sun.deploy.net.HttpResponse;
import hackatonauts.fireshield.listener.helpers.Constants;
import hackatonauts.fireshield.listener.model.FireModel;
import hackatonauts.fireshield.listener.model.FireResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
                Constants.fireEvents,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FireModel>() {
                });

        return fireModel = response.getBody();
    }

    FireModel getFireModel(int days) {
        ResponseEntity<FireModel> response = restTemplate.exchange(
                Constants.fireEvents + Constants.daysParameter + days,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FireModel>() {
                });
        return fireModel = response.getBody();
    }

    HttpStatus postFireResponse(FireResponse fireResponse) {
        HttpEntity<FireResponse> httpEntity = new HttpEntity<>(fireResponse, new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                Constants.baseUrl + Constants.endpointName,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<String>() {
                });

        return response.getStatusCode();
    }
    
    ResponseEntity<String> getCSVEvents() {
        return restTemplate.exchange(Constants.csvGlobal, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
        });
    }
}
