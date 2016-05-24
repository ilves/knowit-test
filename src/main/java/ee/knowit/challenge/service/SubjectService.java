package ee.knowit.challenge.service;

import ee.knowit.challenge.dto.SubjectDto;
import ee.knowit.challenge.entity.SubjectEntity;
import ee.knowit.challenge.exception.NotFoundException;

public interface SubjectService {
	SubjectEntity create(SubjectDto subjectDto);
	SubjectEntity update(SubjectDto subjectDto, SubjectEntity subjectEntity);
	Iterable<SubjectEntity> getSubjects();
	SubjectEntity getById(Integer id) throws NotFoundException;
	void delete(SubjectEntity subjectEntity);
}
