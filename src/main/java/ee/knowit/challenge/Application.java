package ee.knowit.challenge;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("ee.knowit.challenge")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  @Bean
  public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
    return hemf.getSessionFactory();
  }
}
