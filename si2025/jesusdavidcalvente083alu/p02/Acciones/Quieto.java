package si2025.jesusdavidcalvente083alu.p02.Acciones;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Accion;


public class Quieto implements Accion {

	public Quieto(Mundo m) {
		
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		
		return ACTIONS.ACTION_NIL;
	}
	
	public String toString() {
		return "Quieto";
	}
	
	

}
