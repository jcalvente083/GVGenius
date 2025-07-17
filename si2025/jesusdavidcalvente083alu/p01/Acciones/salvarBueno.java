package si2025.jesusdavidcalvente083alu.p01.Acciones;


import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Accion;
import tools.Vector2d;

public class salvarBueno implements Accion{
	
	private Vector2d porPeligro;
	private int[] posAvatar;
	private int dimBlock;
	
	public salvarBueno(Mundo m) {
		dimBlock = m.getDimensionBlock();
	}

	@Override
	public ACTIONS doAction(Mundo m) {
		
		posAvatar = m.getPosAvatar();
		porPeligro = m.getSalvar();
		
		if ((int)porPeligro.x/dimBlock > posAvatar[0]) {return ACTIONS.ACTION_RIGHT;}
		if ((int)porPeligro.x/dimBlock < posAvatar[0]) {return ACTIONS.ACTION_LEFT;}
		if ((int)porPeligro.y/dimBlock < posAvatar[1]) {return ACTIONS.ACTION_UP;}
		if ((int)porPeligro.y/dimBlock > posAvatar[1]) {return ACTIONS.ACTION_DOWN;}
		
		return ACTIONS.ACTION_NIL;
	}
	
	@Override
	public String toString() {
		return "salvarBueno";
	}

}
