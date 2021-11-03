package br.ufpb.edoe.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.ufpb.edoe.entity.Descriptor;

@Repository
public interface DescriptorRepository extends JpaRepository<Descriptor, Integer> {
    
}
