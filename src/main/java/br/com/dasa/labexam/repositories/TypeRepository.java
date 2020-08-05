package br.com.dasa.labexam.repositories;

import br.com.dasa.labexam.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
