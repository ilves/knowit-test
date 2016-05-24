package ee.knowit.challenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "student", schema = "knowit", catalog = "knowit_challenge")
public class StudentEntity {

    private Integer id;
    private String firstName;
    private String lastName;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="pk_sequence", sequenceName="knowit.student_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk_sequence")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
