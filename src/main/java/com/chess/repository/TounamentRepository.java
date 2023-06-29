package com.chess.repository;

import com.chess.entity.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TounamentRepository extends CrudRepository<Tournament, Integer> {
}
