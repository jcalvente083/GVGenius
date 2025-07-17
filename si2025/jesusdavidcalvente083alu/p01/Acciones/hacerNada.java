package si2025.jesusdavidcalvente083alu.p01.Acciones;


import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Accion;

public class hacerNada implements Accion {
	
	private int[] posAvatar;
	private int colCentral;
	
	public hacerNada(Mundo m) {
		colCentral = m.getnCol()/2;
	}
	

	@Override
	public ACTIONS doAction(Mundo m) {
		
		posAvatar = m.getPosAvatar();

		if (colCentral > posAvatar[0]) {return ACTIONS.ACTION_RIGHT;}
		if (colCentral < posAvatar[0]) {return ACTIONS.ACTION_LEFT;}
		
		return ACTIONS.ACTION_NIL;
	}
	
	@Override
	public String toString() {
		return "hacerNada";
	}

}
