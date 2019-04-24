package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/players")
    public List<Player> getPlayers() {
        return playersRepo.findAll();
    }

    @RequestMapping("/games")
    public List<Object> getGames() {
        return gamesRepo.findAll()
                .stream()
                .map(game -> new LinkedHashMap<String, Object>() {{
                    put("id", game.getId());
                    put("created", game.getFecha());
                    put("gamePlayer", game.getGamePlayers());
                }})
                .collect(toList());
    }

  /*  @RequestMapping("/gameplayers/")
    public */



}



