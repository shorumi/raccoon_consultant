package io.raccoonconsultant.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql = "UPDATE laboratories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "laboratories")
public class Laboratory implements Serializable {

  private static final long serialVersionUID = -8656638415841300876L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @CreationTimestamp
  @Column(name = "created_at" )
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at" )
  private Timestamp updatedAt;

  @Column(name = "name")
  @Size(max = 100)
  @NotEmpty(message = "Name is required")
  private String name;

  @Column(name = "address")
  @Size(max = 255)
  @NotEmpty(message = "Address is required")
  private String address;

  @Column(columnDefinition = "varchar(8) default 'ACTIVE'")
  @NotNull(message = "Status should not be null")
  @Enumerated(value = EnumType.STRING)
  private Status status = Status.ACTIVE;

  @Column(name = "deleted")
  private Boolean deleted;

}
