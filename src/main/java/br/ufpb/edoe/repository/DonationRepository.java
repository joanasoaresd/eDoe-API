package br.ufpb.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpb.edoe.entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

}
