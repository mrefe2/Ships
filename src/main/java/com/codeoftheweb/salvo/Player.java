package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.datatransfer.StringSelection;
import java.util.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String email;
    private String password;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany (mappedBy="player", fetch=FetchType.EAGER)
    private Set<Scores> scores = new HashSet<>();

    public Player(){}

    public Player(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Scores> getScore() {
        return scores;
    }

    public void setScore(Set<Scores> scores) { this.scores = scores; }

    public void addScores(Scores score){
        scores.add(score);
        score.setPlayer(this);
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Set<GamePlayer> getGamePlayers(){
        return this.gamePlayers;
    }

    public Map<String, Object> playerToDTO() {
        return new LinkedHashMap<String, Object>(){{
            put("id", id);
            put("username", email);
        }
        };
    }

}

