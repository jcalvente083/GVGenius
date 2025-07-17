package si2025.jesusdavidcalvente083alu.p04.Operadores;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p04.ElementosBusqueda.Operador;

public class Abajo extends Operador{

	public Abajo() {
	}

	@Override
	public ACTIONS getAccion() {
		
		return ACTIONS.ACTION_DOWN;
	}

}