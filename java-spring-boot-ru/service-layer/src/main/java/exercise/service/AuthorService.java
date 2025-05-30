package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorMapper authorMapper;

    public List<AuthorDTO> index() {
        var result = authorRepository.findAll();
        return result.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO show(Long id) {
        var result = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        return authorMapper.map(result);
    }

    public AuthorDTO create(AuthorCreateDTO dto) {
        var author = authorMapper.map(dto);
        authorRepository.save(author);
        var firstName = author.getFirstName();
        var lastName = author.getLastName();
        return authorMapper.map(authorRepository.findByFirstNameAndLastName(firstName, lastName).get());
    }

    public AuthorDTO edit(Long id, AuthorUpdateDTO dto) {
        var result = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorMapper.update(dto, result);
        authorRepository.save(result);
        return authorMapper.map(result);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    // END
}
