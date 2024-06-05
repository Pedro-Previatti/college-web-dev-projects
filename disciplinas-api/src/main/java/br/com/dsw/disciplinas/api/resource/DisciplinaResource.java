package br.com.dsw.disciplinas.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.dsw.disciplinas.api.model.Disciplina;
import br.com.dsw.disciplinas.api.repository.DisciplinaRepository;
import br.com.dsw.disciplinas.api.service.DisciplinaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {
  @Autowired
  private DisciplinaRepository disciplinaRepository;
  @Autowired
  private DisciplinaService disciplinaService;

  @GetMapping
  public List<Disciplina> listar() {
    return disciplinaRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Disciplina> buscaPeloCodigo(@PathVariable Long id) {
    return disciplinaRepository.findById(id);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Disciplina> criar(@RequestBody Disciplina disciplina, HttpServletResponse response) {
    Disciplina disciplinaGravada = disciplinaRepository.save(disciplina);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(disciplinaGravada.getId()).toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(disciplinaGravada);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long id) {
    disciplinaRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Disciplina disciplina) {
    Disciplina disciplinaGravada = disciplinaService.atualizar(id, disciplina);
    return ResponseEntity.ok(disciplinaGravada);
  }
}
