package ee.knowit.challenge.service;

import ee.knowit.challenge.dto.SubjectDto;
import ee.knowit.challenge.entity.SubjectEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;


    @Override
    public SubjectEntity create(SubjectDto subjectDto) {
        return update(subjectDto, new SubjectEntity());
    }

    @Override
    public SubjectEntity update(SubjectDto subjectDto, SubjectEntity subjectEntity) {
        map(subjectDto, subjectEntity);
        subjectRepository.save(subjectEntity);
        return subjectEntity;
    }

    @Override
    public Iterable<SubjectEntity> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public SubjectEntity getById(Integer id) throws NotFoundException {
        SubjectEntity subjectEntity = subjectRepository.findOne(id);
        if (subjectEntity == null) throw new NotFoundException();
        return subjectEntity;
    }

    @Override
    public void delete(SubjectEntity subjectEntity) {
        subjectRepository.delete(subjectEntity);
    }

    private void map(SubjectDto subjectDto, SubjectEntity subjectEntity) {
        subjectEntity.setName(subjectDto.getName());
    }
}