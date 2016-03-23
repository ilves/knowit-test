package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.GradeEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository

public class GradeRepositoryImpl implements GradeRepository {

  @PersistenceContext
  EntityManager entityManager;

  protected Session getCurrentSession()  {
    return entityManager.unwrap(Session.class);
  }

  @Override
  public void create(GradeEntity grade) {
  }

  @Override
  public Collection<GradeEntity> getGrades() {
    Query query = entityManager.createQuery("SELECT g FROM GradeEntity g");
    return (Collection<GradeEntity>) query.getResultList();
  }

  @Override
  public GradeEntity getById(Integer id) {
    return null;
  }
}
