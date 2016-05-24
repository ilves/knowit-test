package ee.knowit.challenge;

import ee.knowit.challenge.config.HibernateConfig;
import ee.knowit.challenge.service.GradeReportService;
import ee.knowit.challenge.service.GradeService;
import ee.knowit.challenge.service.StudentService;
import ee.knowit.challenge.service.SubjectService;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@ComponentScan(basePackages = {"ee.knowit.challenge"}, excludeFilters={
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value=Application.class),
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value=HibernateConfig.class),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "ee.knowit.challenge.repository.*"),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "ee.knowit.challenge.service.*")
})
public class TestContext {

    @Bean
    @Primary
    public StudentService mockStudentService() {
        return Mockito.mock(StudentService.class);
    }

    @Bean
    @Primary
    public GradeService mockGradeService() {
        return Mockito.mock(GradeService.class);
    }

    @Bean
    @Primary
    public SubjectService mockSubjectService() {
        return Mockito.mock(SubjectService.class);
    }

    @Bean
    @Primary
    public GradeReportService mockGradeReportService() {
        return Mockito.mock(GradeReportService.class);
    }

    @Bean
    public EntityManager mockEntityManager() {
        return Mockito.mock(EntityManager.class);
    }

    @Bean
    public EntityManagerFactory mockEntityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }
}
