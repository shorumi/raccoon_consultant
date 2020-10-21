package io.raccoonconsultant.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "exams")
public class Exam implements Serializable {

  private static final long serialVersionUID = -5471356796767756175L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreationTimestamp
  @Column(name = "created_at" )
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Column(name = "name")
  private String name;

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "laboratory_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Laboratory laboratory;

  @OneToOne(fetch = FetchType.EAGER)
  private Type type;

}
