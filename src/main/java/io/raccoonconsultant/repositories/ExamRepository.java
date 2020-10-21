package io.raccoonconsultant.repositories;

import io.raccoonconsultant.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
