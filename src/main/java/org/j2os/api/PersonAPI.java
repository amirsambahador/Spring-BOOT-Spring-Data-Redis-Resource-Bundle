package org.j2os.api;

import org.j2os.common.handle.ErrorHandler;
import org.j2os.domain.Person;
import org.j2os.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
    Bahador, Amirsam
 */
@RestController
@RequestMapping("/person")
public class PersonAPI {
    private final PersonService personService;
    private final ErrorHandler errorHandler;

    public PersonAPI(PersonService personService, ErrorHandler errorHandler) {
        this.personService = personService;
        this.errorHandler = errorHandler;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> onException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorHandler.getError(exception));
    }

    @RequestMapping("/save")
    public List<Person> save(@ModelAttribute Person person) {
        personService.save(person);
        return findAll();
    }

    @RequestMapping("/remove")
    public List<Person> remove(@ModelAttribute Person person) {
        personService.remove(person);
        return findAll();
    }

    @RequestMapping("/findAll")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping("/findOne")
    public Person findOne(@ModelAttribute Person person) {
        return personService.findOne(person);
    }
}
