package si2025.jesusdavidcalvente083alu.p02.Acciones;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Accion;


public class MatarEnfermera implements Accion {

	public MatarEnfermera(Mundo m) {
		
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		
		return ACTIONS.ACTION_USE;
	}
	
	public String toString() {
		return "matarEnfermera";
	}
	
	

}
