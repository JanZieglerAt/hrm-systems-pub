package ch.hrms.assessment.fullstackbackendchallenge.api;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "gender",
        "age"
})
@XmlRootElement(name = "person")
public class Person {

    @XmlElement(required = true)
    @NotBlank(message = "Name must be set")
    protected String name;
    @XmlElement(required = true)
    protected String gender;
    @XmlElement(required = true)
    @Min(value = 1, message = "The age must be greater than 1")
    @Max(value = 99, message = "The age must be less than 99")
    protected BigInteger age;
}
