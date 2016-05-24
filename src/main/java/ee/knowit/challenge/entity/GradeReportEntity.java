package ee.knowit.challenge.entity;

import java.math.BigDecimal;


public class GradeReportEntity {

    private StudentEntity student;

    private Long gradeWeightSum;

    private Long weightSum;

    public GradeReportEntity(Long gradeWeightSum, Long weightSum, StudentEntity student) {
        this.gradeWeightSum = gradeWeightSum;
        this.weightSum = weightSum;
        this.student = student;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public BigDecimal getWeightedGrade() {
        return new BigDecimal(gradeWeightSum)
                .divide(new BigDecimal(weightSum), 2, BigDecimal.ROUND_HALF_UP);
    }
}
