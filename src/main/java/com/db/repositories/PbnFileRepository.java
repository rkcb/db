package com.db.repositories;

import com.db.entities.PbnFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PbnFileRepository extends JpaRepository<PbnFile, Integer> {
}
