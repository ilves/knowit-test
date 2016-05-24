package ee.knowit.challenge.controller;

import ee.knowit.challenge.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface CrudRestController<T, E> {
    T getById(Integer id) throws NotFoundException;
    Collection<T> getAll();
    T create(T dto) throws NotFoundException;
    T update(Integer id, T dto) throws NotFoundException;
    ResponseEntity<Void> delete(Integer id) throws NotFoundException;
}
