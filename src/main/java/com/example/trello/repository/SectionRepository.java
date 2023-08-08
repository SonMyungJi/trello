package com.example.trello.repository;

import com.example.trello.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SectionRepository extends JpaRepository<Section, Long> {
}
