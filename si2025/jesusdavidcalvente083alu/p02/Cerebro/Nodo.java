package si2025.jesusdavidcalvente083alu.p02.Cerebro;

import si2025.jesusdavidcalvente083alu.p02.Mundo;

public abstract class Nodo {

	public Nodo izq, der;
	abstract public Nodo decide(Mundo m);
	
}
