package com.neto.curso.integrationtests.vo.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neto.curso.integrationtests.vo.BookVO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BookEmbeddedVO implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty("bookVOList")
    private List<BookVO> books;

    public BookEmbeddedVO() {}

    public List<BookVO> getBooks() {
        return books;
    }

    public void setBooks(List<BookVO> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEmbeddedVO that = (BookEmbeddedVO) o;
        return Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books);
    }
}
