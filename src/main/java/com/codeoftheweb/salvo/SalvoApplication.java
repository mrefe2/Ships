package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;




@SpringBootApplication
public class SalvoApplication {


	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}


	@Bean
	public CommandLineRunner initData(PlayerRepository playersRepo, GameRepository gamesRepo, GamePlayerRepository gamePlayerRepo, ShipRepository shipsRepo, SalvoRepository salvosRepo, ScoresRepository scoresRepo) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("j.bauer@ctu.gov", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("24"));
			Player player2 = new Player("c.obrian@ctu.gov",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("42"));
			Player player3 = new Player("kim_bauer@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("kb"));
			Player player4 = new Player("t.almeida@ctu.gov", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("mole"));



			Game game1 = new Game(now());
			Game game2 = new Game(now());
			Game game3 = new Game (now());
			Game game4 = new Game (now());
			Game game5 = new Game (now());
			Game game6 = new Game (now());


			List<String> locationsCarrier = Arrays.asList("H1", "H2", "H3", "H4", "H5");
			List<String> locationsBattleship = Arrays.asList("A1", "B1", "C1", "D1");
			List<String> locationsSubmarine = Arrays.asList("E3", "E4", "E5");
			List<String> locationsDestroyer = Arrays.asList("E7", "E8", "E9");
			List<String> locationsPatrolBoat = Arrays.asList("F3", "G3");


			Ship ship1 = new Ship("Carrier", locationsCarrier);
			Ship ship2 = new Ship("Battleship", locationsBattleship);
			Ship ship6 = new Ship("Carrier", locationsCarrier);
			Ship ship7 = new Ship("Battleship", locationsBattleship);
			Ship ship8 = new Ship("Carrier", locationsCarrier);
			Ship ship9 = new Ship("Battleship", locationsBattleship);
			Ship ship10 = new Ship("Carrier", locationsCarrier);
			Ship ship11 = new Ship("Battleship", locationsBattleship);
			Ship ship3 = new Ship("Submarine", locationsSubmarine);
			Ship ship4 = new Ship("Destroyer", locationsDestroyer);
			Ship ship5 = new Ship("Patrol Boat", locationsPatrolBoat);

			List<String> locationsSalvo1 = Arrays.asList("H1", "H2", "H3");
			List<String> locationsSalvo2 = Arrays.asList("A1", "B1", "C1");
			List<String> locationsSalvo3 = Arrays.asList("H4", "H5", "H6");
			List<String> locationsSalvo4 = Arrays.asList("A1", "B1", "C1");
			List<String> locationsSalvo5 = Arrays.asList("A1", "B1", "C1");
			List<String> locationsSalvo6 = Arrays.asList("A1", "B1", "C1");
			List<String> locationsSalvo7 = Arrays.asList("A1", "B1", "C1");
			List<String> locationsSalvo8 = Arrays.asList("A1", "B1", "C1");

			Salvo salvo1 = new Salvo(locationsSalvo1, 1);
			Salvo salvo2 = new Salvo(locationsSalvo2, 1);
			Salvo salvo3 = new Salvo(locationsSalvo3, 2);
			Salvo salvo4 = new Salvo(locationsSalvo4, 2);
			Salvo salvo5 = new Salvo(locationsSalvo5, 2);
			Salvo salvo6 = new Salvo(locationsSalvo6, 2);
			Salvo salvo7 = new Salvo(locationsSalvo7, 2);
			Salvo salvo8 = new Salvo(locationsSalvo8, 2);


			Scores score1 = new Scores(1);
			Scores score2 = new Scores(0);
			Scores score3 = new Scores(0.5);
			Scores score4 = new Scores(0.5);
			Scores score5 = new Scores(1);
			Scores score6 = new Scores(0);
			Scores score7 = new Scores(1);
			Scores score8 = new Scores(0);
			Scores score9 = new Scores(1);
			Scores score10 = new Scores(0);


			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			player1.addScores(score1);
			game1.addScores(score1);
			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship3);
			gamePlayer1.addSalvo(salvo1);
			gamePlayer1.addSalvo(salvo3);

			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			player2.addScores(score2);
			game1.addScores(score2);
			gamePlayer2.addShip(ship2);
			gamePlayer2.addShip(ship4);
			gamePlayer2.addSalvo(salvo2);
			gamePlayer2.addSalvo(salvo4);

			GamePlayer gamePlayer3 = new GamePlayer(game2, player3);
			player3.addScores(score3);
			game2.addScores(score3);
			gamePlayer3.addShip(ship6);
			gamePlayer3.addShip(ship7);
			gamePlayer3.addSalvo(salvo5);
			gamePlayer3.addSalvo(salvo6);

			GamePlayer gamePlayer4 = new GamePlayer(game2, player4);
			player4.addScores(score4);
			game2.addScores(score4);
			gamePlayer4.addShip(ship8);
			gamePlayer4.addShip(ship9);
			gamePlayer4.addSalvo(salvo7);
			gamePlayer4.addSalvo(salvo8);

			GamePlayer gamePlayer5 = new GamePlayer(game3, player1);
			player1.addScores(score5);
			game3.addScores(score5);
			gamePlayer5.addShip(ship6);
			gamePlayer5.addShip(ship7);
			gamePlayer5.addSalvo(salvo5);
			gamePlayer5.addSalvo(salvo6);

			GamePlayer gamePlayer6 = new GamePlayer(game3, player3);
			player3.addScores(score6);
			game3.addScores(score6);
			gamePlayer6.addShip(ship8);
			gamePlayer6.addShip(ship9);
			gamePlayer6.addSalvo(salvo7);
			gamePlayer6.addSalvo(salvo8);

			GamePlayer gamePlayer7 = new GamePlayer(game4, player2);
			player2.addScores(score7);
			game4.addScores(score7);
			gamePlayer7.addShip(ship6);
			gamePlayer7.addShip(ship7);
			gamePlayer7.addSalvo(salvo5);
			gamePlayer7.addSalvo(salvo6);

			GamePlayer gamePlayer8 = new GamePlayer(game4, player4);
			player4.addScores(score8);
			game4.addScores(score8);
			gamePlayer8.addShip(ship8);
			gamePlayer8.addShip(ship9);
			gamePlayer8.addSalvo(salvo7);
			gamePlayer8.addSalvo(salvo8);

			GamePlayer gamePlayer9 = new GamePlayer(game5, player1);
			player1.addScores(score9);
			game5.addScores(score9);
			gamePlayer9.addShip(ship6);
			gamePlayer9.addShip(ship7);
			gamePlayer9.addSalvo(salvo5);
			gamePlayer9.addSalvo(salvo6);

			GamePlayer gamePlayer10 = new GamePlayer(game5, player4);
			player4.addScores(score10);
			game5.addScores(score10);
			gamePlayer10.addShip(ship8);
			gamePlayer10.addShip(ship9);
			gamePlayer10.addSalvo(salvo7);
			gamePlayer10.addSalvo(salvo8);

			GamePlayer gamePlayer11 = new GamePlayer(game6, player2);






				playersRepo.save(player1);
				playersRepo.save(player2);
				playersRepo.save(player3);
				playersRepo.save(player4);

				gamesRepo.save(game1);
				gamesRepo.save(game2);
				gamesRepo.save(game3);
				gamesRepo.save(game4);
				gamesRepo.save(game5);
				gamesRepo.save(game6);

				gamePlayerRepo.save(gamePlayer1);
				gamePlayerRepo.save(gamePlayer2);
				gamePlayerRepo.save(gamePlayer3);
				gamePlayerRepo.save(gamePlayer4);
				gamePlayerRepo.save(gamePlayer5);
				gamePlayerRepo.save(gamePlayer6);
				gamePlayerRepo.save(gamePlayer7);
				gamePlayerRepo.save(gamePlayer8);
				gamePlayerRepo.save(gamePlayer9);
				gamePlayerRepo.save(gamePlayer10);
				gamePlayerRepo.save(gamePlayer11);

				shipsRepo.save(ship1);
				shipsRepo.save(ship2);
				shipsRepo.save(ship3);
				shipsRepo.save(ship4);
				shipsRepo.save(ship5);
				shipsRepo.save(ship6);
				shipsRepo.save(ship7);
				shipsRepo.save(ship8);
				shipsRepo.save(ship9);

				salvosRepo.save(salvo1);
				salvosRepo.save(salvo2);
				salvosRepo.save(salvo3);
				salvosRepo.save(salvo4);
				salvosRepo.save(salvo5);
				salvosRepo.save(salvo6);
				salvosRepo.save(salvo7);
				salvosRepo.save(salvo8);

				scoresRepo.save(score1);
				scoresRepo.save(score2);
				scoresRepo.save(score3);
				scoresRepo.save(score4);
				scoresRepo.save(score5);
				scoresRepo.save(score6);
				scoresRepo.save(score7);
				scoresRepo.save(score8);
				scoresRepo.save(score9);
				scoresRepo.save(score10);

		};

	}

	@Configuration
	class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		PlayerRepository playersRepo;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(email -> {
				Player player = playersRepo.findByEmail(email);
				if (player != null) {
					return new User(player.getEmail(), player.getPassword(),
							AuthorityUtils.createAuthorityList("USER"));
				} else {
					throw new UsernameNotFoundException("Unknown user: " + email);
				}
			});
		}


	}

	@Configuration
	@EnableWebSecurity
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/rest/**").denyAll()
					.antMatchers("/api/**", "/web/**", "/favicon.ico").permitAll().anyRequest().hasAuthority("USER");
			http.formLogin()
					.loginPage("/api/login")
					.usernameParameter("email")
					.passwordParameter("password");
			http.logout().logoutUrl("/api/logout");

			// turn off checking for CSRF tokens
			http.csrf().disable();
			// if user is not authenticated, just send an authentication failure response
			http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
			// if login is successful, just clear the flags asking for authentication
			http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
			// if login fails, just send an authentication failure response
			http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
			// if logout is successful, just send a success response
			http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
		}

		private void clearAuthenticationAttributes(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			}
		}


	}
}
