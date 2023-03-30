package org.j2os.service;

import lombok.SneakyThrows;
import org.j2os.common.exception.RecordNotExist;
import org.j2os.domain.Person;
import org.j2os.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
    Bahador, Amirsam
 */
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void remove(Person person) {
        personRepository.delete(person);
    }

    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll()
                .forEach(list::add);
        return list;
    }

    @SneakyThrows
    public Person findOne(Person person) {
        return personRepository
                .findById(person.getPersonId())
                .orElseThrow(RecordNotExist::new);
    }
}
