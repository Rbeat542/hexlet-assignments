package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookRepository bookRepository;

    //@Autowired
    //AuthorRepository AuthorRepository;

    public List<BookDTO> index() {
        var result = bookRepository.findAll();
        var list = result.stream()
                .map(bookMapper::map)
                .toList();
        return list;
    }

    public BookDTO show(Long id) {
        var result = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        var bookDTO = bookMapper.map(result);
        return bookDTO;
    }

    public BookDTO create(BookCreateDTO dto) {
        var book = bookMapper.map(dto);
        bookRepository.save(book);
        //var title = book.getTitle();
        //var x = bookRepository.findByTitle(title).get();
        return bookMapper.map(book);
    }

    public BookDTO edit(Long id, BookUpdateDTO dto) {
        var result = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(dto, result);
        bookRepository.save(result);
        return bookMapper.map(result);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

// END}
