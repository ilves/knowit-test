package ee.knowit.challenge.controller;

import ee.knowit.challenge.dto.SubjectDto;
import ee.knowit.challenge.entity.SubjectEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController implements CrudRestController<SubjectDto, SubjectEntity> {

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SubjectDto getById(@PathVariable("id") Integer id) throws NotFoundException {
        return createDTO(subjectService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<SubjectDto> getAll() {
        return StreamSupport.stream(subjectService.getSubjects().spliterator(), false)
                .map(SubjectController::createDTO).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public SubjectDto create(@Valid @RequestBody SubjectDto studentDto) {
        return createDTO(subjectService.create(studentDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public SubjectDto update(@PathVariable("id") Integer id,
                             @Valid @RequestBody SubjectDto subjectDto) throws NotFoundException {
        SubjectEntity subjectEntity = subjectService.getById(id);
        return createDTO(subjectService.update(subjectDto, subjectEntity));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws NotFoundException {
        SubjectEntity subjectEntity = subjectService.getById(id);
        subjectService.delete(subjectEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static SubjectDto createDTO(SubjectEntity subjectEntity) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subjectEntity.getName());
        subjectDto.setId(subjectEntity.getId());
        return subjectDto;
    }
}
