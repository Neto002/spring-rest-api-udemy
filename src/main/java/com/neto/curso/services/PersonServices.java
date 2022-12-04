package com.neto.curso.services;

import com.neto.curso.data.vo.v1.PersonVO;
import com.neto.curso.exceptions.ResourceNotFound;
import com.neto.curso.mapper.DozerMapper;
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

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding a person!");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating a person!");

        Person entity = DozerMapper.parseObject(person, Person.class);

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO person) {
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        logger.info("Updating a person!");

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting a person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("No records found for this id!"));

        repository.delete(entity);
    }
}
