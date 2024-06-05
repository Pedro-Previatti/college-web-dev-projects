package br.com.dsw.disciplinas.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsw.disciplinas.api.model.Disciplina;
import br.com.dsw.disciplinas.api.repository.DisciplinaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {
  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @GetMapping
  public List<Disciplina> listar() {
    return disciplinaRepository.findAll();
  }
}
