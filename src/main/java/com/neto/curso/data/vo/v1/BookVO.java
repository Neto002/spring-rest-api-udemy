package com.neto.curso.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import jakarta.persistence.Column;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;

    public BookVO() {

    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BookVO bookVO = (BookVO) o;

        if (!key.equals(bookVO.key)) return false;
        if (!author.equals(bookVO.author)) return false;
        if (!launchDate.equals(bookVO.launchDate)) return false;
        if (!price.equals(bookVO.price)) return false;
        return title.equals(bookVO.title);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + key.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + launchDate.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
