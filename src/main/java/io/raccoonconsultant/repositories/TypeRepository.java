package io.raccoonconsultant.repositories;

import io.raccoonconsultant.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
