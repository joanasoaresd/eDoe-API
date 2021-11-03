package br.ufpb.edoe.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufpb.edoe.entity.Descriptor;

@Repository
public interface DescriptorRepository extends JpaRepository<Descriptor, Integer> {
    Optional<Descriptor> findByName(String name);
}
