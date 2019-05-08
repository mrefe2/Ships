package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime partida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Juegos")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Jugadores")
    private Player player;

    @OneToMany(mappedBy="gamePlayer", fetch= FetchType.EAGER)
    private Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy="gamePlayer", fetch= FetchType.EAGER)
    private Set<Salvo> salvos = new HashSet<>();



    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
        ship.setGamePlayer(this);
    }

    public GamePlayer(){}

    public GamePlayer(LocalDateTime partida, Game game, Player player) {
        this.partida = partida;
        this.game = game;
        this.player = player;
    }


    @JsonIgnore
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getPartida() {
        return partida;
    }
    public void setPartida(LocalDateTime partida) {
        this.partida = partida;
    }



    public Set<Salvo> getSalvo() { return salvos; }

    public void setSalvos (Set<Salvo> salvo) { this.salvos = salvo; }

    public void addSalvo (Salvo salvo) {
        salvos.add(salvo);
        salvo.setGamePlayer(this);
    }
}

