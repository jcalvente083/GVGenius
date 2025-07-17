package si2025.jesusdavidcalvente083alu.p02;

import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import tools.Vector2d;

public class Mundo {

	private static final int PARED = 0;
	private static final int PUERTA = 4;
	private static final int VIRUS = 5;
	private static final int SANO = 10;
	private static final int INFECTADO = 11;
	private static final int ENFERMERA = 12;

	private int numEnfermeras = 0;
	private int numPuertas = 0;
	private int numSanos = 0;
	private int nFila, nCol, dimBlock;

	private boolean infectado;

	private String[][] pintar = null;
	private ArrayList<Observation>[][] mapa = null;
	private int[][] mapaBusqueda;

	private ArrayList<Observation> enfermeras = null;
	private ArrayList<Observation> sanos = null;
	private ArrayList<Observation> puertas = null;
	private ArrayList<Observation> virus = null;

	private int[] avatar = new int[2];

	private Vector2d capturar = new Vector2d(0, 0);
	private Vector2d orientacionAvatar;

	public Mundo(StateObservation stObs) {

		enfermeras = new ArrayList<>();
		sanos = new ArrayList<>();
		puertas = new ArrayList<>();
		virus = new ArrayList<>();

		mapa = stObs.getObservationGrid();
		nFila = mapa[0].length;
		nCol = mapa.length;
		dimBlock = stObs.getBlockSize();
		pintar = new String[nCol][nFila];

		avatar[0] = (int) (stObs.getAvatarPosition().x / dimBlock);
		avatar[1] = (int) (stObs.getAvatarPosition().y / dimBlock);

		mapaBusqueda = new int[nCol][nFila];

		for (int i = 0; i < nFila; i++) {
		    for (int j = 0; j < nCol; j++) {
		        if (mapa[j][i].isEmpty() || mapa[j][i].get(0).itype != PARED) {
		            mapaBusqueda[j][i] = 0; // celda libre
		        } else {
		            mapaBusqueda[j][i] = 1; 
		        }
		    }
		}
		

	
		infectado = false;

		inicializar();

		// pintar();
	}

	public Vector2d getCapturar() {

		Vector2d captura2 = new Vector2d(0, 0);
		
		captura2.x = this.capturar.x/dimBlock;
		captura2.y = this.capturar.y/dimBlock;
		
		return captura2;

	}

	public int getDimensionBlock() {
		return dimBlock;
	}
	
	public Vector2d getOrientacion() {
		return orientacionAvatar;
	}

	public boolean hayEnfermeras() {
		return numEnfermeras != 0;
	}

	public ArrayList<Observation> getEnfermeras() {
		return enfermeras;
	}

	public ArrayList<Observation> getSanos() {
		return sanos;
	}

	public ArrayList<Observation> getPuertas() {
		return puertas;
	}
	

	public boolean hayPuertas() {
		return numPuertas != 0;
	}

	public void actualizar(StateObservation stObs) {
		
		orientacionAvatar = stObs.getAvatarOrientation();

		avatar[0] = (int) (stObs.getAvatarPosition().x / dimBlock);
		avatar[1] = (int) (stObs.getAvatarPosition().y / dimBlock);
		
		mapa = stObs.getObservationGrid();
		
		infectado = stObs.getAvatarType() == 8;

		inicializar();
		// pintar();

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

					case PARED:
						pintar[x][y] = "#";
						break;

					case ENFERMERA:
						numEnfermeras++;
						enfermeras.add(obj);
						pintar[x][y] = "E";
						break;

					case PUERTA:
						pintar[x][y] = "P";
						puertas.add(obj);
						numPuertas++;
						break;

					case SANO:
						pintar[x][y] = "S";
						sanos.add(obj);
						numSanos++;
						break;

					case VIRUS:
						pintar[x][y] = "V";
			 			virus.add(obj);
						break;

					case INFECTADO:
						pintar[x][y] = "I";
						virus.add(obj);
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
		
		if (numEnfermeras > 0) {
			//System.out.println("Buscar Enfermera");
			capturar = enfermeras.get(0).position;
			int distanciaCapturar = distanciaAlAvatar(avatar, capturar);

			for (int i = 1; i < enfermeras.size(); i++) {
				if (distanciaCapturar > distanciaAlAvatar(avatar, enfermeras.get(i).position)) {

					distanciaCapturar = distanciaAlAvatar(avatar, enfermeras.get(i).position);
					capturar = enfermeras.get(i).position;
				}
			}
			
		} else if (this.infectado && numSanos != 0) {
			//System.out.println("Buscar Sano");
			capturar = sanos.get(0).position;
			int distanciaCapturar = distanciaAlAvatar(avatar, capturar);


			for (int i = 1; i < sanos.size(); i++) {
				if (distanciaCapturar > distanciaAlAvatar(avatar, sanos.get(i).position)) {

					distanciaCapturar = distanciaAlAvatar(avatar, sanos.get(i).position);
					capturar = sanos.get(i).position;
				}
			}

		} else {
			//System.out.println("Buscar Virus");
			capturar = virus.get(0).position;
			int distanciaCapturar = distanciaAlAvatar(avatar, capturar);

			for (int i = 1; i < virus.size(); i++) {
				if (distanciaCapturar > distanciaAlAvatar(avatar, virus.get(i).position)) {

					distanciaCapturar = distanciaAlAvatar(avatar, virus.get(i).position);
					capturar = virus.get(i).position;

				}
			}
		}

		pintar[avatar[0]][avatar[1]] = "X";

	}

	private void pintar() {

		// System.out.println("Jugador: [" + avatar[0] + "," + avatar[1] + "]");

		for (int i = 0; i < nFila; i++) {
			for (int j = 0; j < nCol; j++) {
				System.out.print(pintar[j][i]);
			}
			System.out.println("");
		}
	}

	private void vacio() {

		capturar.x = 0;
		capturar.y = 0;

		numPuertas = 0;
		numSanos = 0;
		numEnfermeras = 0;

		enfermeras.clear();
		sanos.clear();
		puertas.clear();
		virus.clear();

		for (int i = 0; i < nFila; i++) {
			for (int j = 0; j < nCol; j++) {
				pintar[j][i] = " ";
			}
		}

	}

	public ArrayList<Observation>[][] getMapa() {
		return mapa;
	}

	public int[] getAvatar() {
		return avatar;
	}

	public int getnFila() {
		return nFila;
	}

	public int getnCol() {
		return nCol;
	}

	public int getNumSanos() {
		return numSanos;
	}

	public int getNumEnfermeras() {
		return numEnfermeras;
	}

	public int getNumPuertas() {
		return numPuertas;
	}

	private int distanciaAlAvatar(int[] posAvatar2, Vector2d cazar) {
		return (Math.abs(((int) (cazar.x / dimBlock) - avatar[0]))
				+ Math.abs(((int) (cazar.y / dimBlock) - avatar[1])));
	}

	public boolean estadoAvatar() {
		return infectado;
	}

	public int[][] getMapaBusqueda() {

		return mapaBusqueda;

	}

}