package ee.knowit.challenge.service;

import ee.knowit.challenge.entity.GradeEntity;

import java.util.Collection;

public interface GradeService {
	public void create(GradeEntity grade);
	public Collection<GradeEntity> getGrades();
	public GradeEntity getById(Integer id);
}
