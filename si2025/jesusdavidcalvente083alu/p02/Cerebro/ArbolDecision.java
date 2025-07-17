package si2025.jesusdavidcalvente083alu.p02.Cerebro;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;

public abstract class ArbolDecision {
	
	private Nodo raiz = null;
	
	public ArbolDecision(Nodo raiz) {
		this.raiz = raiz;
	}
	
	public ACTIONS piensa(Mundo m) {
		return ((NodoAccion)raiz.decide(m)).doAction(m);
	}
}

