package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.StudentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Integer> {}
