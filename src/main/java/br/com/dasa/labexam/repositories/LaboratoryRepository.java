package br.com.dasa.labexam.repositories;

import br.com.dasa.labexam.entities.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
}
