package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private PlayerRepository playersRepo;

    @Autowired
    private GameRepository gamesRepo;

    @Autowired
    private ShipRepository shipsRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;


    @RequestMapping("/players")
    public List<LinkedHashMap<String, Object>> getPlayers() {
        return playersRepo.findAll()
                .stream()
                .map(player -> new LinkedHashMap<String, Object>() {{
                    put("id", player.getId());
                    put("player", player.getGamePlayers());
                }})
                .collect(toList());
    }

    @RequestMapping("/game")
    public List<Object> getGames() {
        return gamesRepo.findAll()
                .stream()
                .map(game -> new LinkedHashMap<String, Object>() {{
                    put("id", game.getId());
                    put("created", game.getFecha());
                    put("gamePlayers", game.getGamePlayers());
                }})
                .collect(toList());
    }


    @RequestMapping("/game_view/{gameplayerId}")
    public LinkedHashMap<String, Object> getGameplayer(@PathVariable Long gameplayerId) {
        return gamePlayerRepo.findById(gameplayerId)
                .map(game -> new LinkedHashMap<String, Object>() {{
                    put("id", game.getId());
                    put("created", game.getPartida());
                    put("gamePlayer", gamePlayerRepo.findById(gameplayerId)
                          .map(game -> new LinkedHashMap<String, Object>() {{
                              put("ships", game.getShips());
                          }}));
                }}).orElse(null);
    }
}

