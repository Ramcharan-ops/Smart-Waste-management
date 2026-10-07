package com.example.collection_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository
       extends JpaRepository<Collection, Long>{
}
