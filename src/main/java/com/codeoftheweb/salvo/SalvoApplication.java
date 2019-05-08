package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;


@SpringBootApplication
public class SalvoApplication {


	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playersRepo, GameRepository gamesRepo, GamePlayerRepository gamePlayerRepo, ShipRepository shipsRepo, SalvoRepository salvosRepo) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("j.bauer@ctu.gov");
			Player player2 = new Player("c.obrian@ctu.gov");
			Player player3 = new Player("kim_bauer@gmail.com");


			Game game1 = new Game(now());
			Game game2 = new Game(now());
			Game game3 = new Game(now());


			List<String> locationsCarrier = Arrays.asList("H1", "H2", "H3","H4","H5");
			List<String> locationsBattleship = Arrays.asList("A1", "B1", "C1","D1");
			List<String> locationsSubmarine = Arrays.asList("E3", "E4", "E5");
			List<String> locationsDestroyer = Arrays.asList("", "", "");
			List<String> locationsPatrolBoat = Arrays.asList("", "");


			Ship ship1 = new Ship("Carrier",locationsCarrier);
			Ship ship2 = new Ship("Battleship", locationsBattleship);
			Ship ship3 = new Ship("Submarine",locationsSubmarine);
			Ship ship4 = new Ship("Destroyer",locationsDestroyer);
			Ship ship5 = new Ship("Patrol Boat",locationsPatrolBoat);

			Salvo salvo = new Salvo();
			Salvo salvo1 = new Salvo();
			Salvo salvo2 = new Salvo();

			GamePlayer gamePlayer1 = new GamePlayer(now(), game1, player1);
			gamePlayer1.addShip(ship1);

			GamePlayer gamePlayer2 = new GamePlayer(now(), game1, player2);
			gamePlayer2.addShip(ship2);

			playersRepo.save(player1);
			playersRepo.save(player2);
			playersRepo.save(player3);

			gamesRepo.save(game1);
			gamesRepo.save(game2);
			gamesRepo.save(game3);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayer2);

			shipsRepo.save(ship1);
			shipsRepo.save(ship2);
			shipsRepo.save(ship3);
			shipsRepo.save(ship4);
			shipsRepo.save(ship5);

			salvosRepo.save(salvo);
			salvosRepo.save(salvo1);
			salvosRepo.save(salvo2);


		};

	}
}
