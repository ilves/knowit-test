package ee.knowit.challenge.controller;

import ee.knowit.challenge.TestUtil;
import ee.knowit.challenge.dto.StudentDto;
import ee.knowit.challenge.entity.StudentEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest extends RestControllerTest {

    @Autowired
    @Qualifier("mockStudentService")
    StudentService mockStudentService;

    @Before
    public void setUp() {
        Mockito.reset(mockStudentService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetExistingStudent() throws Exception {
        when(mockStudentService.getById(1)).thenReturn(John());
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Smith")))
                .andExpect(jsonPath("$.id", is(1)));
        verify(mockStudentService, times(1)).getById(1);
        verifyNoMoreInteractions(mockStudentService);
    }

    @Test
    public void testGetNotExistingStudent() throws Exception {
        when(mockStudentService.getById(1)).thenThrow(new NotFoundException());
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetStudentsList() throws Exception {
        when(mockStudentService.getStudents()).thenReturn(Arrays.asList(John(), Mary()));
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Smith")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Mary")))
                .andExpect(jsonPath("$[1].lastName", is("Blue")));
        verify(mockStudentService, times(1)).getStudents();
        verifyNoMoreInteractions(mockStudentService);
    }

    @Test
    public void testPostNewStudent() throws Exception {
        StudentDto dto = new StudentDto();
        dto.setFirstName("John");
        dto.setLastName("Smith");

        when(mockStudentService.create(any(StudentDto.class))).thenReturn(John());
        mockMvc.perform(post("/api/students")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Smith")));

        ArgumentCaptor<StudentDto> dtoCaptor = ArgumentCaptor.forClass(StudentDto.class);
        verify(mockStudentService, times(1)).create(dtoCaptor.capture());
        verifyNoMoreInteractions(mockStudentService);

        StudentDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getFirstName(), is("John"));
        assertThat(dtoArgument.getLastName(), is("Smith"));
    }

    @Test
    public void testInvalidNewStudentWithoutFirstName() throws Exception {
        StudentDto dto = new StudentDto();
        dto.setLastName("Smith");
        mockMvc.perform(post("/api/students")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
        verifyZeroInteractions(mockStudentService);
    }

    @Test
    public void testDeleteExistingStudent() throws Exception {
        when(mockStudentService.getById(1)).thenReturn(John());
        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());
        verify(mockStudentService, times(1)).getById(1);
    }

    @Test
    public void testDeleteNotExistingStudent() throws Exception {
        when(mockStudentService.getById(1)).thenThrow(new NotFoundException());
        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        StudentDto dto = new StudentDto();
        dto.setFirstName("Mark");
        dto.setLastName("Zuckenberg");

        when(mockStudentService.getById(1)).thenReturn(John());
        when(mockStudentService.update(any(StudentDto.class), any(StudentEntity.class))).thenReturn(Mark());

                mockMvc.perform(put("/api/students/1")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(dto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.firstName", is("Mark")))
                        .andExpect(jsonPath("$.lastName", is("Zuckenberg")));

        ArgumentCaptor<StudentDto> dtoCaptor = ArgumentCaptor.forClass(StudentDto.class);
        ArgumentCaptor<StudentEntity> entityCaptor = ArgumentCaptor.forClass(StudentEntity.class);

        verify(mockStudentService, times(1)).update(dtoCaptor.capture(), entityCaptor.capture());

        StudentDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getFirstName(), is("Mark"));
        assertThat(dtoArgument.getLastName(), is("Zuckenberg"));
    }

    private StudentEntity John() {
        StudentEntity student = new StudentEntity();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Smith");
        return student;
    }

    private StudentEntity Mark() {
        StudentEntity student = new StudentEntity();
        student.setId(1);
        student.setFirstName("Mark");
        student.setLastName("Zuckenberg");
        return student;
    }

    private StudentEntity Mary() {
        StudentEntity student = new StudentEntity();
        student.setId(2);
        student.setFirstName("Mary");
        student.setLastName("Blue");
        return student;
    }
}
