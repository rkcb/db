package com.db.repositories;

import com.db.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogMessageRepository extends JpaRepository<LogMessage, Integer> {
}
