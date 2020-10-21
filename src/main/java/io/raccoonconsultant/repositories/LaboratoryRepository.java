package io.raccoonconsultant.repositories;

import io.raccoonconsultant.entities.Laboratory;
import io.raccoonconsultant.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
  @Query("SELECT l FROM Laboratory as l WHERE l.status = :status")
  List<Laboratory> findAllByStatus(@Param("status") Status status);
}
