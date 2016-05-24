package ee.knowit.challenge.service;

import ee.knowit.challenge.dto.GradeDto;
import ee.knowit.challenge.entity.GradeEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @Override
    public GradeEntity create(GradeDto gradeDto) throws NotFoundException {
        return update(gradeDto, new GradeEntity());
    }

    @Override
    public GradeEntity update(GradeDto gradeDto, GradeEntity gradeEntity) throws NotFoundException {
        map(gradeDto, gradeEntity);
        gradeRepository.save(gradeEntity);
        return gradeEntity;
    }

    @Override
    public void delete(GradeEntity grade) {
        gradeRepository.delete(grade);
    }

    @Override
    public Iterable<GradeEntity> getGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public GradeEntity getById(Integer id) throws NotFoundException {
        GradeEntity gradeEntity = gradeRepository.findOne(id);
        if (gradeEntity == null) throw new NotFoundException();
        return gradeEntity;
    }

    private void map(GradeDto gradeDto, GradeEntity gradeEntity) throws NotFoundException {
        gradeEntity.setGrade(gradeDto.getGrade());
        gradeEntity.setWeight(gradeDto.getWeight());
        gradeEntity.setSubject(subjectService.getById(gradeDto.getSubject().getId()));
        gradeEntity.setStudent(studentService.getById(gradeDto.getStudent().getId()));
    }
}
