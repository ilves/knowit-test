package ee.knowit.challenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "grade", schema = "knowit", catalog = "knowit_challenge")
public class GradeEntity {
  private Integer id;
  private Integer grade;
  private Integer weight;
  private StudentEntity student;
  private SubjectEntity subject;

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "grade")
  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  @Basic
  @Column(name = "weight")
  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  @OneToOne(cascade = CascadeType.ALL)
  public StudentEntity getStudent() {
    return student;
  }

  public void setStudent(StudentEntity student) {
    this.student = student;
  }

  @OneToOne(cascade = CascadeType.ALL)
  public SubjectEntity getSubject() {
    return subject;
  }

  public void setSubject(SubjectEntity subject) {
    this.subject = subject;
  }
}
