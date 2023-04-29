package com.neto.curso.services;

import com.neto.curso.controllers.BookController;
import com.neto.curso.data.vo.v1.BookVO;
import com.neto.curso.exceptions.RequiredObjectIsNullException;
import com.neto.curso.exceptions.ResourceNotFoundException;
import com.neto.curso.mapper.DozerMapper;
import com.neto.curso.model.Book;
import com.neto.curso.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {

        logger.info("Finding all books!");

        List<BookVO> books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books.stream().forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));

        return books;
    }

    public BookVO findById(Long id) throws ResourceNotFoundException {
        logger.info("Finding a book!");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return vo;
    }

    public BookVO create(BookVO book) throws RequiredObjectIsNullException {

        if (book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating a book!");

        Book entity = DozerMapper.parseObject(book, Book.class);

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) throws ResourceNotFoundException {

        if (book == null) {
            throw new RequiredObjectIsNullException();
        }

        Book entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        logger.info("Updating a book!");

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setTitle(book.getTitle());

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) throws ResourceNotFoundException {
        logger.info("Deleting a book!");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        repository.delete(entity);
    }
}
