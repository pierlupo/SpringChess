package com.chess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "game")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "victory")
    private int victory;

    @Column(name = "defeat")
    private int defeat;

    @Column(name = "pat")
    private int pat;

    @Column(name = "winner")
    private String winner;

    @ManyToMany
    private List <Player> players;

    @ManyToMany
    private List<Tournament> tournaments;


}
