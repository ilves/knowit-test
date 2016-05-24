package ee.knowit.challenge.service;

import ee.knowit.challenge.dto.StudentDto;
import ee.knowit.challenge.entity.StudentEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentEntity create(StudentDto studentDto) {
        return update(studentDto, new StudentEntity());
    }

    @Override
    public StudentEntity update(StudentDto studentDto, StudentEntity studentEntity) {
        map(studentDto, studentEntity);
        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    public Iterable<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getById(Integer id) throws NotFoundException {
        StudentEntity student = studentRepository.findOne(id);
        if (student == null) throw new NotFoundException();
        return student;
    }

    @Override
    public void delete(StudentEntity studentEntity) {
        studentRepository.delete(studentEntity);
    }

    private void map(StudentDto studentDto, StudentEntity studentEntity) {
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setLastName(studentDto.getLastName());
    }
}