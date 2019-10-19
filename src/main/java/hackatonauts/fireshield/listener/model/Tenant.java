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
public class Tenant {

    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Long id;
    private String firstName;
    private String surName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Tenant(@JsonProperty("firstName") String firstName, @JsonProperty("surName") String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(firstName, tenant.firstName) &&
                Objects.equals(surName, tenant.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surName);
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
