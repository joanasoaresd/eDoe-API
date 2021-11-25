package br.ufpb.edoe.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "Donation")
public class Donation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @CreationTimestamp
  private LocalDateTime createDateTime;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userDonateId")
  private User userDonate;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userReceptorId")
  private User userReceptor;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itemDonateId")
  private Item itemDonate;
}
