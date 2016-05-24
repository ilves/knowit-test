package ee.knowit.challenge.controller;

import ee.knowit.challenge.dto.GradeReportDto;
import ee.knowit.challenge.entity.GradeReportEntity;
import ee.knowit.challenge.service.GradeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    GradeReportService gradeReportService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<GradeReportDto> getAllWeighted() {
        return StreamSupport.stream(gradeReportService.getGradeReports().spliterator(), false)
                .map(ReportController::createDTO).collect(Collectors.toList());
    }

    public static GradeReportDto createDTO(GradeReportEntity entity) {
        GradeReportDto gradeReportDto = new GradeReportDto();
        gradeReportDto.setWeightedGrade(entity.getWeightedGrade());
        gradeReportDto.setStudent(StudentController.createDTO(entity.getStudent()));
        return gradeReportDto;
    }
}
