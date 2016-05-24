package ee.knowit.challenge.dto;

import java.math.BigDecimal;

public class GradeReportDto {

    private StudentDto student;
    private BigDecimal weightedGrade;

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public BigDecimal getWeightedGrade() {
        return weightedGrade;
    }

    public void setWeightedGrade(BigDecimal weightedGrade) {
        this.weightedGrade = weightedGrade;
    }
}
