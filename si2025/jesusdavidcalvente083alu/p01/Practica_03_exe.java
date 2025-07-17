package si2025.jesusdavidcalvente083alu.p01;
	
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica_03_exe {
	
	static String spGamesCollection = "examples/all_games_sp.csv";
	static String[][] games = Utils.readGames(spGamesCollection);
	
	static int gameIdx = 89;
	
	static String gameName = games[gameIdx][1];
	static String game = games[gameIdx][0];
	
	static boolean visuals = true;
	

	public static void main(String[] args) {

		String agente = "si2025.jesusdavidcalvente083alu.p01.Agente89";

		int iteraciones = 100;
		int seed = -1104602806;
		int level = 4;

//    	lanzar(agente, seed, level);
		for(int i = 0; i < 5; i++) {
			lanzar(agente, new Random(System.currentTimeMillis()).nextInt(), i);
		}
		//lanzar(agente, new Random(System.currentTimeMillis()).nextInt(), level);
//		getStats(agente, iteraciones);

	}

	public static void getStats(String agente, int iteraciones) {

		ArrayList<Resultados> resultados = new ArrayList<Resultados>();
		
		
		for (int nivel = 0; nivel <= 4; nivel++) {
			
			
			int levelIdx = nivel;
			
			String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

			for (int i = 0; i < iteraciones; i++) {
				
				int seed = new Random(System.currentTimeMillis()).nextInt();
				
				double[] resultadoActual = ArcadeMachine.runOneGame(game, level1, visuals, agente, null, seed, 0);
			
				resultados.add(new Resultados(seed, levelIdx,resultadoActual[0]==1.0, resultadoActual[1], resultadoActual[2]));

			}

		}

		guardarResultados(resultados);

	}

	public static void guardarResultados(ArrayList<Resultados> resultados) {

		String archivo = "C:\\Users\\Paulu\\Desktop\\CURSO2425\\SEGUNDO CUATRIMESTRE\\[SI] Sistemas Inteligentes\\prueba\\si2025\\src\\si2025\\jesusdavidcalvente083alu\\p01\\estadisticasRAW.csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			for (Resultados resultado : resultados) {
				LocalDateTime fechaHora = LocalDateTime.now();
				String fechaHoraStr = fechaHora.format(formatter);

				writer.write(fechaHoraStr + ";" + resultado.seed + ";" + resultado.level + ";" + resultado.puntuacion
						+ ";" + resultado.tics + ";" + resultado.ganado);
				
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("ERROR.- No se han podido guardar lso resultados :((");
		}
	}

	public static void lanzar(String Agente, int seed, int levelIdx) {
	
		System.out.println("Seed: "+ seed);
		
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		ArcadeMachine.runOneGame(game, level1, visuals, Agente, null, seed, 0);
	}

}

class Resultados {

	public int seed;
	public double puntuacion;
	public double tics;
	public int level;
	public boolean ganado;

	public Resultados(int seed, int level, boolean ganado, double puntos, double tics) {
		this.seed = seed;
		this.puntuacion = puntos;
		this.tics = tics;
		this.level = level;
		this.ganado = ganado;
	}
}
