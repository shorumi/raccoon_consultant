package br.com.dasa.labexam.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
  private String name;

  @Column(name = "address")
  private String address;

  @Enumerated(value = EnumType.STRING)
  private Status status;

}
