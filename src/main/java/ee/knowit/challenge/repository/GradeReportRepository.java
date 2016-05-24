package ee.knowit.challenge.repository;

import ee.knowit.challenge.entity.GradeReportEntity;

import java.util.List;

public interface GradeReportRepository {
    List<GradeReportEntity> findAll();
}

