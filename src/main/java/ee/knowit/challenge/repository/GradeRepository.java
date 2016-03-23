package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.GradeEntity;

import java.util.Collection;

public interface GradeRepository {

	public void create(GradeEntity grade);
	public Collection<GradeEntity> getGrades();
	public GradeEntity getById(Integer id);

}
