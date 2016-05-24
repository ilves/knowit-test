package ee.knowit.challenge.controller;

import ee.knowit.challenge.dto.StudentDto;
import ee.knowit.challenge.entity.StudentEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/students")
public class StudentController implements CrudRestController<StudentDto, StudentEntity> {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public StudentDto getById(@PathVariable("id") Integer id) throws NotFoundException {
        return createDTO(studentService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<StudentDto> getAll() {
        return StreamSupport.stream(studentService.getStudents().spliterator(), false)
                .map(StudentController::createDTO).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public StudentDto create(@Valid @RequestBody StudentDto studentDto) {
        return createDTO(studentService.create(studentDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public StudentDto update(@PathVariable("id") Integer id,
                                    @Valid @RequestBody StudentDto studentDto) throws NotFoundException {
        StudentEntity studentEntity = studentService.getById(id);
        return createDTO(studentService.update(studentDto, studentEntity));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws NotFoundException {
        StudentEntity studentEntity = studentService.getById(id);
        studentService.delete(studentEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static StudentDto createDTO(StudentEntity studentEntity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(studentEntity.getFirstName());
        studentDto.setLastName(studentEntity.getLastName());
        studentDto.setId(studentEntity.getId());
        return studentDto;
    }
}
