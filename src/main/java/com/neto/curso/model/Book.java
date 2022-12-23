package com.neto.curso.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String author;
    @Column(nullable = false)
    private Date launchDate;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 80)
    private String title;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!author.equals(book.author)) return false;
        if (!launchDate.equals(book.launchDate)) return false;
        if (!price.equals(book.price)) return false;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + launchDate.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
