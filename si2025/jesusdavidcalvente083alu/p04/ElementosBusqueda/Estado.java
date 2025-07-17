package si2025.jesusdavidcalvente083alu.p04.ElementosBusqueda;

import core.game.StateObservation;

public interface Estado {
	
	public  Estado aplicarOperador(Operador o);
	public  boolean isMeta();
	public  void actualizar(StateObservation o);
	
}
