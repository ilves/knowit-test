package ee.knowit.challenge.service;

import ee.knowit.challenge.dto.GradeDto;
import ee.knowit.challenge.entity.GradeEntity;
import ee.knowit.challenge.exception.NotFoundException;

public interface GradeService {
	GradeEntity create(GradeDto gradeDto) throws NotFoundException;
    GradeEntity update(GradeDto gradeDto, GradeEntity gradeEntity) throws NotFoundException;
    void delete(GradeEntity grade);
    Iterable<GradeEntity> getGrades();
	GradeEntity getById(Integer id) throws NotFoundException;
}
