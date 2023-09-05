package ch.hrms.assessment.fullstackbackendchallenge.api;

import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persons")
public class Persons {

    @XmlElement(required = true)
    @Valid
    protected List<Person> person;
}
