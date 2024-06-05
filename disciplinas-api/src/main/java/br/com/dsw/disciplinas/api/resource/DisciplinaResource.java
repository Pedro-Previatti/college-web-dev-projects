package br.com.dsw.disciplinas.api.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dsw.disciplinas.api.model.Disciplina;
import br.com.dsw.disciplinas.api.repository.DisciplinaRepository;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {
  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @GetMapping
  public List<Disciplina> listar() {
    return disciplinaRepository.findAll();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void criar(@RequestBody Disciplina disciplina, HttpServletResponse response) {
    Disciplina disciplinaGravada = disciplinaRepository.save(disciplina);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(disciplinaGravada.getId()).toUri();
    response.setHeader("Location", uri.toASCIIString());
  }

}
