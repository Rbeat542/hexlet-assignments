package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> getAll() {
        return authorService.index();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.show(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@RequestBody AuthorCreateDTO dto) {
        return authorService.create(dto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO edit(@PathVariable Long id, @RequestBody AuthorUpdateDTO dto) {
        return authorService.edit(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
    // END
}
