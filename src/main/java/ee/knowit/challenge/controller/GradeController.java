package ee.knowit.challenge.controller;

import ee.knowit.challenge.entity.GradeEntity;
import ee.knowit.challenge.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GradeController {

  @Autowired
  GradeService gradeService;

  @RequestMapping(value = "/grades", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public Collection<GradeEntity> grades() {
    return gradeService.getGrades();
  }
}
