package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotesRepositories extends JpaRepository<Votes, UUID> {
}
