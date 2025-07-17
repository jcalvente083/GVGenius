package si2025.jesusdavidcalvente083alu.p06;

import java.util.ArrayList;
import core.game.StateObservation;
import core.game.Observation;
import tools.Vector2d;
import java.awt.Dimension;

import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;
import si2025.jesusdavidcalvente083alu.p06.Operadores.*;

public class Mundo {

	public static final int PARED = 0;
	public static final int HUECO = 3;
	public static final int JUGADOR = 4;
	public static final int SETA = 6;
	public static final int LLAVE = 7;
	public static final int PUERTA = 8;
	public static final int BLOQUE = 9;

	private StateObservation so;
	public int nColumnas, nFilas, tambloque;
	Vector2d posAgente, posPuerta;

	public ArrayList<Observation>[][] gridObs;

	public Mundo(StateObservation stateObs) {
		so = stateObs;
		gridObs = so.getObservationGrid();

		Dimension dim = so.getWorldDimension();
		tambloque = this.so.getBlockSize();
		nColumnas = dim.width / tambloque;
		nFilas = dim.height / tambloque;
		System.out.println("Dimensiones del mundo: " + nColumnas + " columnas, " + nFilas + " filas, tamaño de bloque: "
				+ tambloque);
		posAgente = so.getAvatarPosition();

		posAgente.x = (int) (posAgente.x / tambloque);
		posAgente.y = (int) (posAgente.y / tambloque);

		posPuerta = new Vector2d(-1, -1);

		// pintar();

	}

	public void actualizar(StateObservation stateObs) {

		gridObs = so.getObservationGrid();

		posAgente = so.getAvatarPosition();

		posAgente.x = (int) (posAgente.x / tambloque);
		posAgente.y = (int) (posAgente.y / tambloque);

	}

	public ArrayList<Literal> getMetas() {
		ArrayList<Literal> meta = new ArrayList<Literal>();

		meta.add(new Literal("AvatarEnPosicion", (int) posPuerta.x, (int) posPuerta.y));
		meta.add(new Literal("LlaveCogida", -1, -1));

		return meta;
	}

	public ArrayList<Operador> obtenerOperadores() {
		ArrayList<Operador> op = new ArrayList<Operador>();
		for (int y = 0; y < nFilas; y++) {
			for (int x = 0; x < nColumnas; x++) {
				if (gridObs[x][y].isEmpty()) {
					op.add(new MoverAvatarAbajo(x, y));
					op.add(new MoverAvatarArriba(x, y));
					op.add(new MoverAvatarDerecha(x, y));
					op.add(new MoverAvatarIzquierda(x, y));

					op.add(new MoverPiedraAbajo(x, y));
					op.add(new MoverPiedraArriba(x, y));
					op.add(new MoverPiedraDerecha(x, y));
					op.add(new MoverPiedraIzquierda(x, y));
				} else if (gridObs[x][y].get(0).itype != PARED) {
					
					if (gridObs[x][y].get(0).itype == LLAVE) {

						// op.add(new CogerLlaveHaciaArriba(x, y+1));
						// op.add(new CogerLlaveHaciaAbajo(x,y-1));
						// op.add(new CogerLlaveHaciaDerecha(x+1, y));
						// op.add(new CogerLlaveHaciaIzquierda(x-1, y));

						op.add(new CogerLlave(x, y));

					}
					if (gridObs[x][y].get(0).itype == SETA) {
						// op.add(new CogerSeta(x,y));

					}

					if (gridObs[x][y].get(0).itype == HUECO) {
						
						op.add(new TaparAgujeroHaciaAbajo(x, y - 2));
		        		op.add(new TaparAgujeroHaciaArriba(x, y+2));
		        		op.add(new TaparAgujeroHaciaDerecha(x-2, y));
		        		op.add(new TaparAgujeroHaciaIzquierda(x+2, y));
					}
					
					op.add(new MoverAvatarAbajo(x, y));
					op.add(new MoverAvatarArriba(x, y));
					op.add(new MoverAvatarDerecha(x, y));
					op.add(new MoverAvatarIzquierda(x, y));
					op.add(new MoverPiedraAbajo(x, y));
					op.add(new MoverPiedraArriba(x, y));
					op.add(new MoverPiedraDerecha(x, y));
					op.add(new MoverPiedraIzquierda(x, y));
				}

			}
		}

		return op;

	}

	public ArrayList<Literal> getEstadoInicial() {
		ArrayList<Literal> EI = new ArrayList<Literal>();

		for (int y = 0; y < nFilas; y++) {
			for (int x = 0; x < nColumnas; x++) {

				ArrayList<Observation> celda = gridObs[x][y];

				if (celda.isEmpty()) {
					EI.add(new Literal("EspacioLibre", x, y));
				}

				for (Observation obs : celda) {
					switch (obs.itype) {
					case PUERTA:

						EI.add(new Literal("PuertaEn", x, y));
						EI.add(new Literal("EspacioLibre", x, y));
						posPuerta.x = x;
						posPuerta.y = y;
						break;

					case JUGADOR:
						EI.add(new Literal("AvatarEnPosicion", x, y));
						break;

					case HUECO:
						EI.add(new Literal("HuecoEn", x, y));
						break;

					case BLOQUE:
						EI.add(new Literal("BloqueEn", x, y));
						break;

					case LLAVE:

						EI.add(new Literal("LlaveEn", x, y));
						EI.add(new Literal("EspacioLibre", x, y));
						break;

					case SETA:
						EI.add(new Literal("SetaEn", x, y));
						EI.add(new Literal("EspacioLibre", x, y));
						break;
					}
				}
			}
		}
		return EI;
	}

	public void pintar() {
		System.out.println("Estado del mundo:");
		for (int y = 0; y < nFilas; y++) {
			for (int x = 0; x < nColumnas; x++) {
				ArrayList<Observation> celda = gridObs[x][y];
				if (celda.isEmpty()) {
					System.out.print(" . ");
				} else {
					System.out.print(" " + celda.get(0).itype + " ");
				}
			}
			System.out.println();
		}
		System.out.println("Posición del agente: " + posAgente);
		System.out.println("Posición de la puerta: " + posPuerta);
		System.out.println();

	}

}
