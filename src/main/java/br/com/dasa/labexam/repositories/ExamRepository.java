package br.com.dasa.labexam.repositories;

import br.com.dasa.labexam.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
