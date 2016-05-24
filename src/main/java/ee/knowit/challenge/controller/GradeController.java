package ee.knowit.challenge.controller;

import ee.knowit.challenge.dto.GradeDto;
import ee.knowit.challenge.entity.GradeEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/grades")
public class GradeController implements CrudRestController<GradeDto, GradeEntity> {

    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GradeDto getById(@PathVariable("id") Integer id) throws NotFoundException {
        return createDTO(gradeService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<GradeDto> getAll() {
        return StreamSupport.stream(gradeService.getGrades().spliterator(), false)
                .map(GradeController::createDTO).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public GradeDto create(@Valid @RequestBody GradeDto dto) throws NotFoundException {
        return createDTO(gradeService.create(dto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public GradeDto update(@PathVariable("id") Integer id,
                           @Valid @RequestBody GradeDto gradeDto) throws NotFoundException {
        GradeEntity gradeEntity = gradeService.getById(id);
        return createDTO(gradeService.update(gradeDto, gradeEntity));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws NotFoundException {
        GradeEntity gradeEntity = gradeService.getById(id);
        gradeService.delete(gradeEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static GradeDto createDTO(GradeEntity entity) {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(entity.getId());
        gradeDto.setGrade(entity.getGrade());
        gradeDto.setWeight(entity.getWeight());
        gradeDto.setStudent(StudentController.createDTO(entity.getStudent()));
        gradeDto.setSubject(SubjectController.createDTO(entity.getSubject()));
        return gradeDto;
    }
}
