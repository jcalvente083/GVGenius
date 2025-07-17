package si2025.jesusdavidcalvente083alu.p02.Cerebro;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;

public interface Accion {
	
	public ACTIONS doAction(Mundo m);
	public String toString();

}
