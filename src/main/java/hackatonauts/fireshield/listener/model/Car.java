package hackatonauts.fireshield.listener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class Car {

    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Long id;
    private String brandName;
    private String model;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Car(@JsonProperty("brandName") String brandName, @JsonProperty("model") String model) {
        this.brandName = brandName;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brandName, car.brandName) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName, model);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brandName='" + brandName + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
