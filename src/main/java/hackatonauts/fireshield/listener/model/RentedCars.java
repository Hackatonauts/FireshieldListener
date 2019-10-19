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
public class RentedCars {

    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Long id;
    private Long tenantId;
    private Long carId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RentedCars(@JsonProperty("tenant") Long tenantId, @JsonProperty("car") Long carId) {
        this.tenantId = tenantId;
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentedCars rentedCar = (RentedCars) o;
        return Objects.equals(tenantId, rentedCar.tenantId) &&
                Objects.equals(carId, rentedCar.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantId, carId);
    }

    @Override
    public String toString() {
        return "Position{" +
                "brandName='" + tenantId + '\'' +
                ", model='" + carId + '\'' +
                '}';
    }
}
