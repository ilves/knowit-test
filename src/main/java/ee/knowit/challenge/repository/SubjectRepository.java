package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.SubjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectRepository extends PagingAndSortingRepository<SubjectEntity, Integer> {}
