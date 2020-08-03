package br.com.dasa.labexam.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "laboratories")
public class Laboratory implements Serializable {

  private static final long serialVersionUID = -8656638415841300876L;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @CreatedDate
  @Column(name = "created_at" )
  private Date createdAt;

  @LastModifiedDate
  @Column(name = "updated_at" )
  private Date updatedAt;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
  private Set<Exam> exams = new HashSet<>();

}
