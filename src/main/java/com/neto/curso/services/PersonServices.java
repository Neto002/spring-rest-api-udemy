package com.neto.curso.services;

import com.neto.curso.exceptions.ResourceNotFound;
import com.neto.curso.model.Person;
import com.neto.curso.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {

        logger.info("Finding all people!");

        return repository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Finding a person!");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));
    }

    public Person create(Person person) {
        logger.info("Creating a person!");
        return repository.save(person);
    }

    public Person update(Person person) {
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        logger.info("Updating a person!");

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        repository.delete(entity);
    }
}
