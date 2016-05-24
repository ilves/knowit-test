package ee.knowit.challenge.service;

import ee.knowit.challenge.entity.GradeReportEntity;
import ee.knowit.challenge.repository.GradeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeReportServiceImpl implements GradeReportService {

    @Autowired
    GradeReportRepository gradeReportRepository;

    @Override
    public Iterable<GradeReportEntity> getGradeReports() {
        return gradeReportRepository.findAll();
    }
}
