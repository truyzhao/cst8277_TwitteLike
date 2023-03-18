package com.truby.cst8277.repository;

import com.truby.cst8277.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Follower, UUID> {
}
