package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String type;
    @ElementCollection
    @Column(name="locations")
    private List<String> locations;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayerID")
    private GamePlayer gamePlayer;


    public Ship(String type, List<String> locations) {
        this.type = type;
        this.locations = locations;
    }

    public Ship() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getLocations() {
        return locations;
    }

    public void setLocations(List locations) {
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

}
