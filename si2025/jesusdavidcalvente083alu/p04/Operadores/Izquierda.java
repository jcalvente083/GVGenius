package si2025.jesusdavidcalvente083alu.p04.Operadores;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p04.ElementosBusqueda.Operador;

public class Izquierda extends Operador{

	public Izquierda() {
	}

	@Override
	public ACTIONS getAccion() {
		
		return ACTIONS.ACTION_LEFT;
	}

}