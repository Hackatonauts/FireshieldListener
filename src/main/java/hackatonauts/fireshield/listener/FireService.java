package hackatonauts.fireshield.listener;

import hackatonauts.fireshield.listener.helpers.Constants;
import hackatonauts.fireshield.listener.helpers.Logger;
import hackatonauts.fireshield.listener.model.FireEvent;
import hackatonauts.fireshield.listener.model.FireModel;
import hackatonauts.fireshield.listener.model.FireResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.bcel.Const;
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
                Constants.fireEvents + Constants.filter + Constants.daysParameter + days,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FireModel>() {
                });
        return fireModel = response.getBody();
    }

    FireModel getFireModelWithClosedStatus(int days) {
        ResponseEntity<FireModel> response = restTemplate.exchange(
                Constants.fireEvents + Constants.filter + Constants.statusClosedParameter + Constants.and + Constants.daysParameter + days,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FireModel>() {
                });
        return fireModel = response.getBody();
    }

    ResponseEntity<String> postFireResponse(FireResponse fireResponse) {
        HttpEntity<FireResponse> httpEntity = new HttpEntity<>(fireResponse, new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                Constants.baseUrl + Constants.endpointName,
                HttpMethod.POST,
                httpEntity,
                String.class);

//        Logger.logDebug(response.getBody());
        return response;
    }

    HttpStatus putFireResponseClosed(FireResponse fireResponse) {
        ResponseEntity<String> response = restTemplate.exchange(
                Constants.baseUrl + Constants.endpointName + Constants.slash + fireResponse.getId() + Constants.close,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<String>() {
                });

        return response.getStatusCode();
    }

    HttpStatus putFireResponseClosed(FireEvent fireEvent) {
        ResponseEntity<String> response = restTemplate.exchange(
                Constants.baseUrl + Constants.endpointName + Constants.slash + fireEvent.getId() + Constants.close,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<String>() {
                });

        return response.getStatusCode();
    }

    FireResponse getFireResponseFromService(FireResponse fireResponse) {
        HttpEntity<FireResponse> httpEntity = new HttpEntity<>(fireResponse, new HttpHeaders());

        ResponseEntity<FireResponse> response = restTemplate.exchange(
                Constants.baseUrl + Constants.endpointName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FireResponse>() {
                });

        return response.getBody();
    }

    ResponseEntity<String> getCSVEvents() {
        return restTemplate.exchange(Constants.csvGlobal, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
        });
    }


}
