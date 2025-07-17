package si2025.jesusdavidcalvente083alu.p01.Acciones;


import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Accion;
import ontology.Types.ACTIONS;

public class vaciarBolsa implements Accion {
	
	private int[] posCarcel;
	private int[] posAgente;

	public vaciarBolsa(Mundo m) {
		posCarcel = m.getCarcel();
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		posAgente = m.getPosAvatar();
		
		if (posCarcel[0] < posAgente[0]) {return ACTIONS.ACTION_LEFT;}
		if (posCarcel[0] > posAgente[0]) {return ACTIONS.ACTION_RIGHT;}
		if (posCarcel[1] > posAgente[1]) {return ACTIONS.ACTION_DOWN;}
		if (posCarcel[1] < posAgente[1]) {return ACTIONS.ACTION_UP;}
				
		return ACTIONS.ACTION_NIL;
	}
	
	@Override
	public String toString() {
		return "vaciarBolsa";
	}

}
