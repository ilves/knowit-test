package ee.knowit.challenge.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class SubjectDto {

    private Integer id;

    @NotEmpty
    @Length(max = 100)
    private String name;

    public SubjectDto(Integer id) {
        this.id = id;
    }

    public SubjectDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
