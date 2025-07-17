package si2025.jesusdavidcalvente083alu.p06.Operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;

public class MoverAvatarArriba extends Operador {

	public MoverAvatarArriba(int x, int y) {
		
		super("MoverAvatarArriba", x, y);
				
		ArrayList<Literal> pre = new ArrayList<>();
		pre.add(new Literal("AvatarEnPosicion", x, y));   
		pre.add(new Literal("EspacioLibre", x, y-1));
		super.setPre(pre);
		
		ArrayList<Literal> add = new ArrayList<>();
		add.add(new Literal("AvatarEnPosicion", x, y-1));
		add.add(new Literal("EspacioLibre", x, y));
		super.setAdd(add);
		
		ArrayList<Literal> del = new ArrayList<>();
		del.add(new Literal("AvatarEnPosicion", x, y));
		del.add(new Literal("EspacioLibre", x, y-1));
		super.setDel(del);
		
		ACTIONS accion = ACTIONS.ACTION_UP;
		super.setAccion(accion);
		
		
	}


}
