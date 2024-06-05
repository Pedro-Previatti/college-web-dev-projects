package br.com.dsw.disciplinas.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.dsw.disciplinas.api.model.Disciplina;
import br.com.dsw.disciplinas.api.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
  @Autowired
  private DisciplinaRepository disciplinaRepository;

  public Disciplina atualizar(Long id, Disciplina disciplina) {
    Optional<Disciplina> disciplinaGravada = disciplinaRepository.findById(id);

    if (!disciplinaGravada.isPresent()) {
      throw new EmptyResultDataAccessException(1);
    }

    Disciplina disciplinaTemp = disciplinaGravada.get();
    BeanUtils.copyProperties(disciplina, disciplinaTemp, "id");
    return disciplinaRepository.save(disciplinaTemp);
  }
}
