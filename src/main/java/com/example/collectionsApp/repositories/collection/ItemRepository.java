package com.example.collectionsApp.repositories.collection;

import com.example.collectionsApp.models.collection.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCollection_id(Long id);
    Optional<Item> findById(Long id);
    void deleteById(Long id);
}
