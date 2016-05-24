package ee.knowit.challenge.controller;

import ee.knowit.challenge.TestUtil;
import ee.knowit.challenge.dto.SubjectDto;
import ee.knowit.challenge.entity.SubjectEntity;
import ee.knowit.challenge.exception.NotFoundException;
import ee.knowit.challenge.service.SubjectService;
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

public class SubjectControllerTest extends RestControllerTest {

    @Autowired
    @Qualifier("mockSubjectService")
    SubjectService mockSubjectService;

    @Before
    public void setUp() {
        Mockito.reset(mockSubjectService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetExistingSubject() throws Exception {
        when(mockSubjectService.getById(1)).thenReturn(English());
        mockMvc.perform(get("/api/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("English")))
                .andExpect(jsonPath("$.id", is(1)));
        verify(mockSubjectService, times(1)).getById(1);
        verifyNoMoreInteractions(mockSubjectService);
    }

    @Test
    public void testGetNotExistingSubject() throws Exception {
        when(mockSubjectService.getById(1)).thenThrow(new NotFoundException());
        mockMvc.perform(get("/api/subjects/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetSubjectsList() throws Exception {
        when(mockSubjectService.getSubjects()).thenReturn(Arrays.asList(English(), Math()));
        mockMvc.perform(get("/api/subjects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("English")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Math")));
        verify(mockSubjectService, times(1)).getSubjects();
        verifyNoMoreInteractions(mockSubjectService);
    }

    @Test
    public void testPostNewSubject() throws Exception {
        SubjectDto dto = new SubjectDto();
        dto.setName("Math");

        when(mockSubjectService.create(any(SubjectDto.class))).thenReturn(Math());
        mockMvc.perform(post("/api/subjects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Math")));

        ArgumentCaptor<SubjectDto> dtoCaptor = ArgumentCaptor.forClass(SubjectDto.class);
        verify(mockSubjectService, times(1)).create(dtoCaptor.capture());
        verifyNoMoreInteractions(mockSubjectService);

        SubjectDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getName(), is("Math"));
    }

    @Test
    public void testInvalidNewSubjectWithoutName() throws Exception {
        SubjectDto dto = new SubjectDto();
        mockMvc.perform(post("/api/subjects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
        verifyZeroInteractions(mockSubjectService);
    }

    @Test
    public void testDeleteExistingSubject() throws Exception {
        when(mockSubjectService.getById(1)).thenReturn(English());
        mockMvc.perform(delete("/api/subjects/1"))
                .andExpect(status().isNoContent());
        verify(mockSubjectService, times(1)).getById(1);
    }

    @Test
    public void testDeleteNotExistingSubject() throws Exception {
        when(mockSubjectService.getById(1)).thenThrow(new NotFoundException());
        mockMvc.perform(delete("/api/subjects/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateSubject() throws Exception {
        SubjectDto dto = new SubjectDto();
        dto.setName("Biology");

        when(mockSubjectService.getById(1)).thenReturn(English());
        when(mockSubjectService.update(any(SubjectDto.class), any(SubjectEntity.class))).thenReturn(Biology());

                mockMvc.perform(put("/api/subjects/1")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(dto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.name", is("Biology")));

        ArgumentCaptor<SubjectDto> dtoCaptor = ArgumentCaptor.forClass(SubjectDto.class);
        ArgumentCaptor<SubjectEntity> entityCaptor = ArgumentCaptor.forClass(SubjectEntity.class);

        verify(mockSubjectService, times(1)).update(dtoCaptor.capture(), entityCaptor.capture());

        SubjectDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getName(), is("Biology"));
    }

    private SubjectEntity English() {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(1);
        subjectEntity.setName("English");
        return subjectEntity;
    }

    private SubjectEntity Math() {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(2);
        subjectEntity.setName("Math");
        return subjectEntity;
    }

    private SubjectEntity Biology() {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(1);
        subjectEntity.setName("Biology");
        return subjectEntity;
    }
}
