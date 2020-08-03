package br.com.dasa.labexam.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "exams")
public class Exam implements Serializable {

  private static final long serialVersionUID = 2079625182324496080L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreatedDate
  @Column(name = "created_at" )
  private Date createdAt;

  @LastModifiedDate
  @Column(name = "updated_at" )
  private Date updatedAt;

  @Column(name = "name")
  private String name;

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @ManyToOne
  private Laboratory laboratory;

  @OneToOne(fetch = FetchType.EAGER)
  private Type type;
}
