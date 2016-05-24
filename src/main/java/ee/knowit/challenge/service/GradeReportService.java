package ee.knowit.challenge.service;

import ee.knowit.challenge.entity.GradeReportEntity;

public interface GradeReportService {
    Iterable<GradeReportEntity> getGradeReports();
}
