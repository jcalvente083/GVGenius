package si2025.jesusdavidcalvente083alu.p01;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import tools.Vector2d;

public class Mundo {

	private int numEnemigosD = 0;
	private int numEnemigosI = 0;
	
	private int Mochila = 0;
	private int nFila, nCol, dimBlock, tics;

	private String[][] pintar = null;
	private ArrayList<Observation>[][] mapa = null;
	private ArrayList<Observation>[] portales = null;
	private ArrayList<Observation>[] enemigos = null;
	private ArrayList<Observation> nubes = null;
	private ArrayList<Observation> buenos = null;
	private ArrayList<Observation> malos = null;
	private ArrayList<Observation> cayendo = null;
	private ArrayList<Observation> cayendoPeligro = null;
	private Observation puertaIzq = null;
	private Observation puertaDcha = null;
	private ArrayList<Observation> puertas = null;
	private int[] avatar = new int[2];
	private int[] carcel = new int[2];
	private Vector2d salvar = new Vector2d(0, 0);
	private Vector2d capturar = new Vector2d(0, 0);

	public Mundo(StateObservation stObs) {

		nubes = new ArrayList<>();
		buenos = new ArrayList<>();
		malos = new ArrayList<>();
		cayendo = new ArrayList<>();
		cayendoPeligro = new ArrayList<>();
		puertas = new ArrayList<>();
		mapa = stObs.getObservationGrid();
		nFila = mapa[0].length;
		nCol = mapa.length;
		dimBlock = stObs.getBlockSize();
		pintar = new String[nCol][nFila];
		tics = stObs.getGameTick();

		avatar[0] = (int) (stObs.getAvatarPosition().x / dimBlock);
		avatar[1] = (int) (stObs.getAvatarPosition().y / dimBlock);
		
		portales = stObs.getPortalsPositions();
		enemigos = stObs.getNPCPositions();

		inicializar();
		pintar();
	
	}

	public Vector2d getCapturar() {
		return capturar;
	}

	public Vector2d getSalvar() {
		return salvar;
	}

	public ArrayList<Observation> getCayendoPeligro() {
		return cayendoPeligro;
	}

	public int getDimensionBlock() {
		return dimBlock;
	}

	public boolean mochilaLlena() {
		return Mochila == 8;
	}

	public boolean hayMalos() {
		return enemigos != null;
	}

	public ArrayList<Observation> getMalos() {
		return malos;
	}

	public ArrayList<Observation> getBuenos() {
		return buenos;
	}

	public ArrayList<Observation> getCayendo() {
		return cayendo;
	}

	public ArrayList<Observation> getNubes() {
		return nubes;
	}

	public Observation getPuertaIzq() {
		return puertaIzq;
	}

	public Observation getPuertaDcha() {
		return puertaDcha;
	}

	public int getNumEnemDer() {
		return numEnemigosD;
	}
	
	public int getNumEnemigos() {
		return numEnemigosD + numEnemigosI;
	}

	public int getNumEnemIzq() {
		return numEnemigosI;
	}
	
	public int getTicsJuego() {
		
		return tics;
	}

	public void actualizar(StateObservation stObs) {
		if (stObs.getAvatarResources().get(13) != null) {
			Mochila = stObs.getAvatarResources().get(13);
		}

		avatar[0] = (int) (stObs.getAvatarPosition().x / dimBlock);
		avatar[1] = (int) (stObs.getAvatarPosition().y / dimBlock);
		
		mapa = stObs.getObservationGrid();
		
		enemigos = stObs.getNPCPositions();
		tics = stObs.getGameTick();
		
		inicializar();
		//pintar();

	}

	private void inicializar() {
		vacio();

		for (int i = 0; i < nFila; i++) {
			for (int j = 0; j < nCol; j++) {
				if (!mapa[j][i].isEmpty()) {
					Observation obj = mapa[j][i].get(0);
					int x = (int) (obj.position.x / dimBlock);
					int y = (int) (obj.position.y / dimBlock);
					switch (obj.itype) {
					case 0: // PARED
						pintar[x][y] = "#";
						break;
					case 3: // NUBE
						pintar[x][y] = "N";
						nubes.add(obj);
						break;
					case 4: // SUELO
						pintar[x][y] = "_";
						break;
					case 5: 
						pintar[x][y] = " ";
						break;
					case 6: // CÁRCEL
						pintar[x][y] = "C";
						carcel[0] = x; carcel[1] = y;
					    break;
					case 11: // ENEMIGO DCHA
						pintar[x][y] = "<";
						malos.add(obj);
						numEnemigosD++;
						break;
					case 12: // ENEMIGO IZQ
						pintar[x][y] = ">";
						malos.add(obj);
						numEnemigosI++;
						break;
					case 18:
						pintar[x][y] = "|";
						cayendo.add(obj);
						break;
					case 19: 
						pintar[x][y] = "-";
						break;
					case 16: // NPC
						pintar[x][y] = "P";
						buenos.add(obj);
						break;
					case 20: // AVATAR 
						pintar[x][y] = "A";
						break;
					default:
						pintar[x][y] = "" + mapa[x][y].get(0).itype;
						break;
					}
				} else {
					pintar[j][i] = " ";
				}
			}
		}

		if (!malos.isEmpty()) {
			capturar = malos.get(0).position;
			int distanciaCapturar = distanciaAlAvatar(avatar, capturar);

			for (int i = 1; i < malos.size(); i++) {
				if (distanciaCapturar > distanciaAlAvatar(avatar, malos.get(i).position)) {

					distanciaCapturar = distanciaAlAvatar(avatar, malos.get(i).position);
					capturar = malos.get(i).position;
				}
			}
		}

		if (!cayendo.isEmpty()) {
			for (int i = 0; i < cayendo.size(); i++) {
				if (!nubeDebajo(cayendo.get(i))) {
					cayendoPeligro.add(cayendo.get(i));
				}
			}
		}
		
		if (!cayendoPeligro.isEmpty()) {
			salvar = cayendoPeligro.get(0).position;
			int distanciaSalvarSuelo = (int) salvar.y / dimBlock;
			int distanciaSalvar = distanciaAlAvatar(avatar, salvar);
			for (int i = 1; i < cayendoPeligro.size(); i++) {

				int dSuelo = (int) cayendoPeligro.get(i).position.y / dimBlock;
				int d = distanciaAlAvatar(avatar, cayendoPeligro.get(i).position);

				if (distanciaSalvarSuelo < dSuelo) {
					distanciaSalvarSuelo = dSuelo;
					salvar = cayendoPeligro.get(i).position;
				} else if (distanciaSalvarSuelo == dSuelo && distanciaSalvar > d) {
					distanciaSalvar = d;
					salvar = cayendoPeligro.get(i).position;

				}
			}
		}
		
		if (portales != null) {
			for (int i = 0; i < portales.length; i++) {
				for (int j = 0; j < portales[i].size(); j++) {
					
					int x = (int) (portales[i].get(j).position.x / dimBlock);
					int y = (int) (portales[i].get(j).position.y / dimBlock);
					
					switch (portales[i].get(j).itype) {
					case 9:
						pintar[x][y] = "L";
						puertaIzq = portales[i].get(j);
						break;
					case 8:
						pintar[x][y] = "R";
						puertaDcha = portales[i].get(j);
						break;
					default:
						pintar[x][y] = "" + portales[i].get(j).itype;
						break;
					}
				}
			}
		}

		pintar[avatar[0]][avatar[1]] = "X";

	}

	private void pintar() {

		System.out.println("Jugador: [" + avatar[0] + "," + avatar[1] + "]");
		System.out.println("Mochila: " + (mochilaLlena()? "Llena" : Mochila));

		for (int i = 0; i < nFila; i++) {
			for (int j = 0; j < nCol; j++) {
				System.out.print(pintar[j][i]);
			}
			System.out.println("");
		}
	}

	private void vacio() {

		numEnemigosI = 0;
		numEnemigosD = 0;
		
		nubes.clear();
		cayendoPeligro.clear();
		cayendo.clear();
		puertas.clear();
		malos.clear();
		buenos.clear();
		
		salvar.x = 0;
		salvar.y = 0;
		
		capturar.x = 0;
		capturar.y = 0;

		for (int i = 0; i < nFila; i++) {
			for (int j = 0; j < nCol; j++) {
				pintar[j][i] = " ";
			}
		}

	}

	public ArrayList<Observation>[][] getMapa() {
		return mapa;
	}

	public int[] getPosAvatar() {
		return avatar;
	}

	public int[] getCarcel() {
		return carcel;
	}

	public int getnFila() {
		return nFila;
	}

	public int getnCol() {
		return nCol;
	}

	private boolean nubeDebajo(Observation observation) {
		int y = (int) observation.position.y / dimBlock;
		int x = (int) observation.position.x / dimBlock;
		boolean hayNube = false;

		for (int i = y; i < nFila; i++) {
			if (!mapa[x][i].isEmpty() && mapa[x][i].get(0).itype == 3) {
				hayNube = true;
			}
		}

		return hayNube;
	}

	private int distanciaAlAvatar(int[] posAvatar2, Vector2d salvar) {
		return (Math.abs(((int) (salvar.x / dimBlock) - avatar[0]))
				+ Math.abs(((int) (salvar.y / dimBlock) - avatar[1])));
	}

}
