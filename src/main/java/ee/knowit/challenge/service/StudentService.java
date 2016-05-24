package ee.knowit.challenge.service;

import ee.knowit.challenge.dto.StudentDto;
import ee.knowit.challenge.entity.StudentEntity;
import ee.knowit.challenge.exception.NotFoundException;

public interface StudentService {
	StudentEntity create(StudentDto studentDto);
	StudentEntity update(StudentDto studentDto, StudentEntity studentEntity);
	Iterable<StudentEntity> getStudents();
	StudentEntity getById(Integer id) throws NotFoundException;
	void delete(StudentEntity studentEntity);
}
