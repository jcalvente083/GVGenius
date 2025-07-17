package si2025.jesusdavidcalvente083alu.p02.Acciones;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.AEstrella.AEstrella;
import si2025.jesusdavidcalvente083alu.p02.AEstrella.AEstrella.ACCIONES;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Accion;
import tools.Vector2d;

public class InfectarNPC implements Accion {

	private int[][] mapaBusqueda = null;

	public InfectarNPC(Mundo m) {
		mapaBusqueda = m.getMapaBusqueda();
	}

	@Override
	public ACTIONS doAction(Mundo m) {

		Vector2d captura = m.getCapturar();

		AEstrella ae = new AEstrella();
		int[] av = m.getAvatar();
		int xAv = av[0];
		int yAv = av[1];
		int xCaptura = (int) captura.x;
		int yCaptura = (int) captura.y;

		java.util.List<ACCIONES> act = AEstrella.aStarSearch(xAv, yAv, xCaptura, yCaptura, mapaBusqueda);

		if (act.size() > 0) {
			switch (act.get(0)) {
			case UP:
				return ACTIONS.ACTION_UP;
			case DOWN:
				return ACTIONS.ACTION_DOWN;
			case LEFT:
				return ACTIONS.ACTION_LEFT;
			case RIGHT:
				return ACTIONS.ACTION_RIGHT;
			case NIL:
				return ACTIONS.ACTION_NIL;

			}
		}
		return ACTIONS.ACTION_NIL;

	}
	
	public String toString() {
		return "InfectarNPC";
	}

}
