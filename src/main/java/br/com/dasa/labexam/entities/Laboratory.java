package br.com.dasa.labexam.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
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
  @NotBlank(message = "Name is required")
  private String name;

  @Column(name = "address")
  @Size(max = 255)
  @NotBlank(message = "Address is required")
  private String address;

  @Column(columnDefinition = "varchar(8) default 'ACTIVE'")
  @NotNull(message = "Status should not be null")
  @Enumerated(value = EnumType.STRING)
  private Status status = Status.ACTIVE;

}
