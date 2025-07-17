package si2025.jesusdavidcalvente083alu.p01.Acciones;


import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Accion;

public class irCentro implements Accion {

	private int[] posAvatar;
	private int colCentral;
	
	public irCentro(Mundo m) {
		colCentral = m.getnCol()/2;
	}
	
	@Override
	public ACTIONS doAction(Mundo m) {
		
		posAvatar = m.getPosAvatar();

		if (m.getTicsJuego() > 800) {return ACTIONS.ACTION_NIL;}
		
		if (colCentral > posAvatar[0]) {return ACTIONS.ACTION_RIGHT;}
		if (colCentral < posAvatar[0]) {return ACTIONS.ACTION_LEFT;}
		
		return ACTIONS.ACTION_NIL;
		
	}
	
	@Override
	public String toString() {
		return "irCentro";
	}

}
