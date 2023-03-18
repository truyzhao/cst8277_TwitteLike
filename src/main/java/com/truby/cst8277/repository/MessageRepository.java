package com.truby.cst8277.repository;

import com.truby.cst8277.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("SELECT m FROM Message m WHERE m.user.id = ?1")
    public List<Message> findByUserId(UUID userId);
}
