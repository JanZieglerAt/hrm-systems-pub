package ch.hrms.assessment.fullstackbackendchallenge.repository;


import ch.hrms.assessment.fullstackbackendchallenge.api.Person;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Store of persons.
 */
@Component
public class PersonRepository {
    private final Map<String, Person> repo = new HashMap<>();

    public void add(Person person){
        repo.putIfAbsent(person.getName(), person);
    }

    public void update(Person person){
        repo.replace(person.getName(), person);
    }

    public Person get(String name){ return repo.get(name); }

    public List<Person> get(){
        return List.copyOf(repo.values());
    }

    public int count(){
        return repo.size();
    }
}
