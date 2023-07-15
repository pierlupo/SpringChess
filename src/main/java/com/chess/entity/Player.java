package com.chess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "player")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

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
    private boolean isInTournament;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Game> games;

    @OneToMany
    private List<Tournament> tournaments;
}

