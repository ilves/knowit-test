package ee.knowit.challenge.controller;

import ee.knowit.challenge.TestUtil;
import ee.knowit.challenge.entity.GradeReportEntity;
import ee.knowit.challenge.entity.StudentEntity;
import ee.knowit.challenge.service.GradeReportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GradeReportControllerTest extends RestControllerTest {

    @Autowired
    @Qualifier("mockGradeReportService")
    GradeReportService mockGradeReportService;

    @Before
    public void setUp() {
        Mockito.reset(mockGradeReportService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetGradeReportList() throws Exception {
        when(mockGradeReportService.getGradeReports()).thenReturn(Arrays.asList(JohnGradeReport(), MaryGradeReport()));
        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].weightedGrade", is(5.0)))
                .andExpect(jsonPath("$[1].weightedGrade", is(4.5)));
        verify(mockGradeReportService, times(1)).getGradeReports();
        verifyNoMoreInteractions(mockGradeReportService);
    }

    private GradeReportEntity JohnGradeReport() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setFirstName("John");
        studentEntity.setLastName("Smith");
        return new GradeReportEntity(10L, 2L, studentEntity);
    }

    private GradeReportEntity MaryGradeReport() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(2);
        studentEntity.setFirstName("Mary");
        studentEntity.setLastName("Blue");
        return new GradeReportEntity(18L, 4L, studentEntity);
    }
}
