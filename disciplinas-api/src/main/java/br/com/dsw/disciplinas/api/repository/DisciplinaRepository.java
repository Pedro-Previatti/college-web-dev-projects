package br.com.dsw.disciplinas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dsw.disciplinas.api.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
