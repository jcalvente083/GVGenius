package si2025.jesusdavidcalvente083alu.p02.Cerebro;

import si2025.jesusdavidcalvente083alu.p02.Mundo;

public class NodoDecision extends Nodo{

	private Decision pregunta = null;
	
	public NodoDecision(Decision c) {
		pregunta = c;
		this.izq = null;
		this.der = null;
	}
	
	
	
	public void setIzq(Nodo izq) {
		this.izq = izq;
	}

	public void setDer(Nodo der) {
		this.der = der;
	}

	public Nodo getBranch(Mundo m) {
		if (pregunta.seCumple(m)) {
			return izq;
		} else {
			
			return der;
		}
		
		
	}
	
	public Nodo decide(Mundo m) {
		return this.getBranch(m).decide(m);
	}
}
