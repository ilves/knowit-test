package ee.knowit.challenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject", schema = "knowit", catalog = "knowit_challenge")
public class SubjectEntity {
  private Integer id;
  private String name;

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
