package si2025.jesusdavidcalvente083alu;

import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica_XX_exe {

    public static void main(String[] args) {
		
		//Load available games
		String spGamesCollection =  "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		//Game settings
		boolean visuals = true;
		int seed = new Random().nextInt();
				
		// Game and level to play
		int gameIdx  = 4;
		int levelIdx = 1; 
		
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		
		// 1. This starts a game, in a level, played by a human.
		ArcadeMachine.playOneGame(game, level1, null, seed);
		System.out.println("Game played: " + gameName + " - Level: " + levelIdx);
					

		System.exit(0);

    }
}


