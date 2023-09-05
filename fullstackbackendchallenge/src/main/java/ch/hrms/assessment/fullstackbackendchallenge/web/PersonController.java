package ch.hrms.assessment.fullstackbackendchallenge.web;

import ch.hrms.assessment.fullstackbackendchallenge.api.Person;
import ch.hrms.assessment.fullstackbackendchallenge.api.Persons;
import ch.hrms.assessment.fullstackbackendchallenge.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService service;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> add(@Valid @RequestBody Persons persons) {

        log.info("Add persons {}", persons);
        service.addPersons(persons.getPerson());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> update(@RequestBody Persons persons) {

        log.info("Add persons {}", persons);
        service.updatePersons(persons.getPerson());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {

        log.info("Get all persons");
        return ResponseEntity.ok(service.getPersons());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Person> get(@PathVariable String name) {

        log.info("Get person {}", name);
        return ResponseEntity.ok(service.getPerson(name));
    }
}
