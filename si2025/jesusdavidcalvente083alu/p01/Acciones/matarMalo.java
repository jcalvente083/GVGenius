package si2025.jesusdavidcalvente083alu.p01.Acciones;


import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Accion;
import tools.Vector2d;

public class matarMalo implements Accion{
	
	private Vector2d posMalo;
	private int[] posAvatar;
	private int dimBlock;
	
	public matarMalo(Mundo m) {
		dimBlock = m.getDimensionBlock();
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		
		posAvatar = m.getPosAvatar();
		posMalo = m.getCapturar();
		
		if ((int)posMalo.y/dimBlock < posAvatar[1]) {return ACTIONS.ACTION_UP;}
		if ((int)posMalo.y/dimBlock > posAvatar[1]) {return ACTIONS.ACTION_DOWN;}
		if ((int)posMalo.x/dimBlock > posAvatar[0]) {return ACTIONS.ACTION_RIGHT;}
		if ((int)posMalo.x/dimBlock < posAvatar[0]) {return ACTIONS.ACTION_LEFT;}
		
		return ACTIONS.ACTION_NIL;
	}
	
	@Override
	public String toString() {
		return "matarMalo";
	}

}
