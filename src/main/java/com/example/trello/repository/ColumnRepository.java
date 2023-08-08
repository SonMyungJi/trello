package com.example.trello.repository;

import com.example.trello.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
}
