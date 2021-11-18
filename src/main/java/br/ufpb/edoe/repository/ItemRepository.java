package br.ufpb.edoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpb.edoe.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
  List<Item> findAllByDescriptorId(int id);
  List<Item> findAllByDescriptorNameContainingIgnoreCase(String search);
}
