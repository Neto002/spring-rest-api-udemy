package com.neto.curso.integrationtests.vo.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neto.curso.integrationtests.vo.PersonVO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PersonEmbeddedVO implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty("personVOList")
    private List<PersonVO> people;

    public PersonEmbeddedVO() {}

    public List<PersonVO> getPeople() {
        return people;
    }

    public void setPeople(List<PersonVO> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEmbeddedVO that = (PersonEmbeddedVO) o;
        return Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(people);
    }
}
