package ee.knowit.challenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject", schema = "knowit", catalog = "knowit_challenge")
public class SubjectEntity {
    private Integer id;
    private String name;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "knowit.subject_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
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
