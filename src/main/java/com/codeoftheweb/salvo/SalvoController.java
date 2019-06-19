package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/player", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody Player player) {
        if (player.getEmail().isEmpty() || player.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (playersRepo.findByEmail(player.getEmail()) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        playersRepo.save(new Player(player.getEmail(), passwordEncoder.encode(player.getPassword())));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Player getLoginPlayer(Authentication authentication) {
        if (authentication != null) {
            return playersRepo.findByEmail(authentication.getName());
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity <Map<String, Object>> createGame(Authentication authentication){
        Player player = getLoginPlayer(authentication);
        if (player != null) {
                Map<String, Object> newGame = new LinkedHashMap<>();
                Game game = new Game(LocalDateTime.now());
                GamePlayer gamePlayer = new GamePlayer(game, player);
                gamesRepo.save(game);
                gamePlayerRepo.save(gamePlayer);

                newGame.put("gpId", gamePlayer.getId());

                return new ResponseEntity<>(newGame, HttpStatus.CREATED);

            } else {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "Log in!");
            }} , HttpStatus.UNAUTHORIZED);
        }}






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
    public LinkedHashMap<String, Object> getGames(Authentication authentication) {
        Player player = getLoginPlayer(authentication);
        Optional<Player> py = Optional.ofNullable(player);
        return new LinkedHashMap<String, Object>(){{
           put("player", py.map(Player::playerToDTO).orElse(null));
           put("games",  gamesRepo.findAll()
                   .stream()
                   .map(game -> new LinkedHashMap<String, Object>() {{
                       put("id", game.getId());
                       put("created", game.getFecha());
                       put("gamePlayers", game.getGamePlayers());
                   }})
                   .collect(toList()));
        }};

    }


    @RequestMapping("/game_view/{gameplayerId}")
    public Optional<LinkedHashMap<String, Object>> getGameplayer(@PathVariable Long gameplayerId, Authentication authentication) {
        Player player = getLoginPlayer(authentication);
        Optional<GamePlayer> gamePlayer = gamePlayerRepo.findById(gameplayerId);
        if (player != null && gamePlayer.isPresent())
            return gamePlayerRepo.findById(gameplayerId)
                    .map(game -> new LinkedHashMap<String, Object>() {{
                        put("id", game.getId());
                        put("gamePlayer", gamePlayerRepo.findById(gameplayerId)
                                .map(game -> new LinkedHashMap<String, Object>() {{
                                    put("ships", game.getShips());
                                    put("salvos", game.getSalvos());
                                }}));
                    }});
            return null;
        }
    }


