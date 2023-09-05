package ch.hrms.assessment.fullstackbackendchallenge.service;

import ch.hrms.assessment.fullstackbackendchallenge.api.Person;
import ch.hrms.assessment.fullstackbackendchallenge.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Person service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository repository;

    /**
     * @return all stored persons.
     */
    public List<Person> getPersons(){

        log.debug("get all persons");
        return repository.get().stream().sorted(Comparator.comparing(Person::getName)).toList();
    }

    /**
     *
     * @param name of the desired person.
     * @return {@link Person} if stored or else null.
     */
    public Person getPerson(String name){

        log.debug("get person {}", name);
        return repository.get(name);
    }

    /**
     * Stores given persons.
     * @param persons to be stored.
     */
    public void addPersons(@NonNull List<Person> persons){

        log.debug("add persons {}", persons);
        persons.forEach(repository::add);
    }

    /**
     * Updates given persons if stored.
     * @param persons to be updated.
     */
    public void updatePersons(@NonNull List<Person> persons){

        log.debug("update persons {}", persons);
        persons.forEach(repository::update);
    }
}
