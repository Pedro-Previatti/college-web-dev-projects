package br.com.dsw.disciplinas.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.dsw.disciplinas.api.model.Disciplina;
import br.com.dsw.disciplinas.api.repository.DisciplinaRepository;

public class DisciplinaResource {
  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @GetMapping
  public List<Disciplina> listar() {
    return disciplinaRepository.findAll();
  }
}
