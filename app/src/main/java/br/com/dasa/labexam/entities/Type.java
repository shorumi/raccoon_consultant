package br.com.dasa.labexam.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "types")
public class Type implements Serializable {

  private static final long serialVersionUID = 5347822088690075406L;

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
  public String name;

}
