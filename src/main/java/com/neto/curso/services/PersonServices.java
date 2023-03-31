package com.neto.curso.services;

import com.neto.curso.controllers.PersonController;
import com.neto.curso.data.vo.v1.PersonVO;
import com.neto.curso.exceptions.RequiredObjectIsNullException;
import com.neto.curso.exceptions.ResourceNotFound;
import com.neto.curso.mapper.DozerMapper;
import com.neto.curso.model.Person;
import com.neto.curso.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        List<PersonVO> people = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        people.stream()
                .forEach(
                        p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
                );
        return people;
    }

    public PersonVO findById(Long id) {

        logger.info("Finding a person!");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) throws RequiredObjectIsNullException {

        if (person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating a person!");

        Person entity = DozerMapper.parseObject(person, Person.class);

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) {
            throw new RequiredObjectIsNullException();
        }

        Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        logger.info("Updating a person!");

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        repository.delete(entity);
    }
}
