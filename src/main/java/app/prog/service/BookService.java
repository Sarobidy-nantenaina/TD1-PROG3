package app.prog.service;

import app.prog.Exception.BookNotFoundException;
import app.prog.model.Book;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    /*
    TODO-1: Only title and author should be provided during the creation of a new book.
    The ID is created by default by the database so it should not be provided at all.
    Therefore, the pageNumber and the releaseDate exists also in the Book model.
    A solution to create a book without the ID, the pageNumber and the releaseDate ?
     */
    public List<Book> createBooks(List<Book> toCreate) {
        return repository.saveAll(toCreate);
    }

    /*
    TODO-2-i: Why the createBooks and the updateBooks use the same repository method saveAll ?
    TODO-2-ii : Only ID, title and author should be provided during the update.
    Therefore, the pageNumber and the release date exists also in the Book model.
    A solution to update a book without the pageNumber and the releaseDate ?
     */
    public List<Book> updateBooks(List<Book> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    //TODO-3: should I use Integer here or int ? Why ?
    public Book deleteBook(int bookId) {
        /*
        TIPS: From the API, the Class Optional<T> is :
        A container object which may or may not contain a non-null value.
        If a value is present, isPresent() returns true.
        If no value is present, the object is considered empty and isPresent() returns false.

        T is the type of the value, for example : here the class type is Book
         */
        Optional<Book> optional = repository.findById(String.valueOf(bookId));
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return optional.get();
        } else {
        /*
        TODO-5 : The exception appears as an internal server error, status 500.
        We all know that the appropriate error status is the 404 Not Found.
        Any solution to do this ?
        These links may help you :
        Link 1 : https://www.baeldung.com/spring-response-entity
        Link 2 : https://www.baeldung.com/exception-handling-for-rest-with-spring
         */
            throw new BookNotFoundException(bookId);
        }
    }
}
