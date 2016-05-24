package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.GradeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GradeRepository extends PagingAndSortingRepository<GradeEntity, Integer> {}
