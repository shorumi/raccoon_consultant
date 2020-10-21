package io.raccoonconsultant.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "types")
public class Type implements Serializable {

  private static final long serialVersionUID = 5347822088690075406L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreationTimestamp
  @Column(name = "created_at" )
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at" )
  private Timestamp updatedAt;

  @Column(name = "name")
  public String name;

}
