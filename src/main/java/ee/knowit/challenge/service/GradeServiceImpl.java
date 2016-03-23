package ee.knowit.challenge.service;

import ee.knowit.challenge.entity.GradeEntity;
import ee.knowit.challenge.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GradeServiceImpl implements GradeService {

  @Autowired
  GradeRepository gradeRepository;

  @Override
  public void create(GradeEntity grade) {
  }

  @Override
  public Collection<GradeEntity> getGrades() {
    return gradeRepository.getGrades();
  }

  @Override
  public GradeEntity getById(Integer id) {
    return null;
  }
}
