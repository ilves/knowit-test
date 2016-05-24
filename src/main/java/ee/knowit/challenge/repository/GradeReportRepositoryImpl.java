package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.GradeReportEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GradeReportRepositoryImpl implements GradeReportRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<GradeReportEntity> findAll() {
        Query query = entityManager.createQuery("select " +
                "new ee.knowit.challenge.entity.GradeReportEntity(sum(g.grade*g.weight), sum(g.weight), g.student) " +
                "from GradeEntity g " +
                "group by g.student");
        return (List<GradeReportEntity>) query.getResultList();
    }
}
