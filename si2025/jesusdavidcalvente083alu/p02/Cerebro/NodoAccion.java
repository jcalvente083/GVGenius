package si2025.jesusdavidcalvente083alu.p02.Cerebro;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;

public class NodoAccion extends Nodo {
	
	private Accion a = null;
	
	public NodoAccion(Accion a) {
		this.a = a;
		this.izq = null;
		this.der = null;
	}
	
	public Accion getAccion() {
		return a;
	}
	
	public ACTIONS doAction(Mundo m) {
		
		return a.doAction(m);
		
	}
	
	public Nodo decide(Mundo m) {
		return this;
	}

}
