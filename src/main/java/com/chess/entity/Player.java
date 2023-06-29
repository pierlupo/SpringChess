package com.chess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.List;

@Entity
@Table(name = "player")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Player extends AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "victory")
    private int victory;

    @Column(name = "defeat")
    private int defeat;

    @Column(name = "pat")
    private int pat;

    @Column(name = "num_of_games")
    private int nbrOfGames;

    @Column(name = "elo")
    private int elo;

    @Column(name = "is_in_tournament")
    private boolean IsInTournament;

    @ManyToMany
    private List<Game> games;

    @ManyToMany
    private List<Tournament> tournaments;
}

