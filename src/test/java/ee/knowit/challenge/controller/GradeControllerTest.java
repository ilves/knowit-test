package ee.knowit.challenge.controller;

import ee.knowit.challenge.TestUtil;
import ee.knowit.challenge.dto.GradeDto;
import ee.knowit.challenge.dto.StudentDto;
import ee.knowit.challenge.dto.SubjectDto;
import ee.knowit.challenge.entity.GradeEntity;
import ee.knowit.challenge.entity.StudentEntity;
import ee.knowit.challenge.entity.SubjectEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.service.GradeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GradeControllerTest extends RestControllerTest {

    @Autowired
    @Qualifier("mockGradeService")
    GradeService mockGradeService;

    @Before
    public void setUp() {
        Mockito.reset(mockGradeService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetExistingGrade() throws Exception {
        when(mockGradeService.getById(1)).thenReturn(JohnGrade());
        mockMvc.perform(get("/api/grades/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.grade", is(5)))
                .andExpect(jsonPath("$.weight", is(2)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.subject.id", is(1)))
                .andExpect(jsonPath("$.student.id", is(1)));
        verify(mockGradeService, times(1)).getById(1);
        verifyNoMoreInteractions(mockGradeService);
    }

    @Test
    public void testGetNotExistingGrade() throws Exception {
        when(mockGradeService.getById(1)).thenThrow(new NotFoundException());
        mockMvc.perform(get("/api/grades/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetGradeList() throws Exception {
        when(mockGradeService.getGrades()).thenReturn(Arrays.asList(JohnGrade(), MaryGrade()));
        mockMvc.perform(get("/api/grades"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].grade", is(5)))
                .andExpect(jsonPath("$[0].weight", is(2)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].grade", is(3)))
                .andExpect(jsonPath("$[1].weight", is(1)));
        verify(mockGradeService, times(1)).getGrades();
        verifyNoMoreInteractions(mockGradeService);
    }

    @Test
    public void testPostNewGrade() throws Exception {
        GradeDto dto = new GradeDto();
        dto.setGrade(5);
        dto.setWeight(2);
        dto.setStudent(new StudentDto(1));
        dto.setSubject(new SubjectDto(1));

        when(mockGradeService.create(any(GradeDto.class))).thenReturn(JohnGrade());
        when(mockGradeService.update(any(GradeDto.class), any(GradeEntity.class))).thenReturn(JohnGrade());

        mockMvc.perform(post("/api/grades")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.grade", is(5)))
                .andExpect(jsonPath("$.weight", is(2)));

        ArgumentCaptor<GradeDto> dtoCaptor = ArgumentCaptor.forClass(GradeDto.class);
        verify(mockGradeService, times(1)).create(dtoCaptor.capture());
        verifyNoMoreInteractions(mockGradeService);

        GradeDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getGrade(), is(5));
        assertThat(dtoArgument.getWeight(), is(2));
    }

    @Test
    public void testInvalidNewGradeWithoutSubject() throws Exception {
        GradeDto dto = new GradeDto();
        dto.setGrade(2);
        dto.setWeight(5);
        mockMvc.perform(post("/api/grades")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
        verifyZeroInteractions(mockGradeService);
    }

    @Test
    public void testDeleteExistingGrade() throws Exception {
        when(mockGradeService.getById(1)).thenReturn(JohnGrade());
        mockMvc.perform(delete("/api/grades/1"))
                .andExpect(status().isNoContent());
        verify(mockGradeService, times(1)).getById(1);
    }

    @Test
    public void testDeleteNotExistingGrade() throws Exception {
        when(mockGradeService.getById(1)).thenThrow(new NotFoundException());
        mockMvc.perform(delete("/api/grades/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateGrade() throws Exception {
        GradeDto dto = new GradeDto();
        dto.setGrade(4);
        dto.setWeight(3);
        dto.setStudent(new StudentDto(1));
        dto.setSubject(new SubjectDto(1));

        when(mockGradeService.getById(1)).thenReturn(JohnGrade());
        when(mockGradeService.update(any(GradeDto.class), any(GradeEntity.class))).thenReturn(JohnNewGrade());

                mockMvc.perform(put("/api/grades/1")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(dto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.grade", is(4)));

        ArgumentCaptor<GradeDto> dtoCaptor = ArgumentCaptor.forClass(GradeDto.class);
        ArgumentCaptor<GradeEntity> entityCaptor = ArgumentCaptor.forClass(GradeEntity.class);

        verify(mockGradeService, times(1)).update(dtoCaptor.capture(), entityCaptor.capture());

        GradeDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getGrade(), is(4));
    }

    private GradeEntity JohnGrade() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setFirstName("John");
        studentEntity.setLastName("Smith");

        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(1);
        subjectEntity.setName("English");

        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setId(1);
        gradeEntity.setGrade(5);
        gradeEntity.setWeight(2);
        gradeEntity.setStudent(studentEntity);
        gradeEntity.setSubject(subjectEntity);
        return gradeEntity;
    }

    private GradeEntity MaryGrade() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(2);
        studentEntity.setFirstName("Mary");
        studentEntity.setLastName("Blue");
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(2);
        subjectEntity.setName("Math");
        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setId(2);
        gradeEntity.setGrade(3);
        gradeEntity.setWeight(1);
        gradeEntity.setStudent(studentEntity);
        gradeEntity.setSubject(subjectEntity);
        return gradeEntity;
    }

    private GradeEntity JohnNewGrade() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setFirstName("John");
        studentEntity.setLastName("Smith");
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(1);
        subjectEntity.setName("English");
        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setId(1);
        gradeEntity.setGrade(4);
        gradeEntity.setWeight(3);
        gradeEntity.setStudent(studentEntity);
        gradeEntity.setSubject(subjectEntity);
        return gradeEntity;
    }
}
