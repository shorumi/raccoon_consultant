package br.com.dasa.labexam.repositories;

import br.com.dasa.labexam.entities.Laboratory;
import org.springframework.data.repository.CrudRepository;

public interface LaboratoryRepository extends CrudRepository<Laboratory, Long> {
}
