package ee.knowit.challenge.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class StudentDto {

    private Integer id;

    @NotEmpty
    @Length(max = 100)
    private String firstName;

    @NotEmpty
    @Length(max = 100)
    private String lastName;

    public StudentDto(Integer id) {
        this.id = id;
    }

    public StudentDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
