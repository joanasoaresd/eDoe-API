package br.ufpb.edoe.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufpb.edoe.entity.Descriptor;

@Repository
public interface DescriptorRepository extends JpaRepository<Descriptor, String> {
    List<Descriptor> findByNameContainingIgnoreCase(String name);
}
