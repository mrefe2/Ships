package com.codeoftheweb.salvo;

		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.annotation.Bean;

		import java.time.LocalDateTime;
		import java.util.Arrays;
		import java.util.List;
		import java.util.Set;

		import static java.time.LocalDateTime.*;


@SpringBootApplication
public class SalvoApplication {


	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playersRepo, GameRepository gamesRepo, GamePlayerRepository gamePlayerRepo, ShipRepository shipsRepo) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("j.bauer@ctu.gov");
			playersRepo.save(player1);
			Player player2 = new Player("c.obrian@ctu.gov");
			playersRepo.save(player2);
			Player player3 = new Player("kim_bauer@gmail.com");
			playersRepo.save(player3);

			Game game1 = new Game(now());
			gamesRepo.save(game1);
			Game Game2 = new Game(now());
			gamesRepo.save(Game2);
			Game Game3 = new Game(now());
			gamesRepo.save(Game3);

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


			GamePlayer gamePlayer1 = new GamePlayer(now(), game1, player1);
			gamePlayer1.addShip(ship1);


			GamePlayer gamePlayer2 = new GamePlayer(now(), game1, player2);
			gamePlayer2.addShip(ship2);



			shipsRepo.save(ship1);
			shipsRepo.save(ship2);
			shipsRepo.save(ship3);
			shipsRepo.save(ship4);
			shipsRepo.save(ship5);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayer2);

		};

	}
}
