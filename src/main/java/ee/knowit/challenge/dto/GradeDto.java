package ee.knowit.challenge.dto;

import javax.validation.constraints.NotNull;

public class GradeDto {

    private Integer id;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer weight;

    @NotNull
    private StudentDto student;

    @NotNull
    private SubjectDto subject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }
}
