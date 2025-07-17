package si2025.jesusdavidcalvente083alu.p06.Operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;

public class TaparAgujeroHaciaIzquierda extends Operador {

	public TaparAgujeroHaciaIzquierda(int x, int y) {
		super("TaparAgujeroHaciaIzquierda", x, y);
		
		
		ArrayList<Literal> pre = new ArrayList<>();
		pre.add(new Literal("AvatarEnPosicion", x, y));
		pre.add(new Literal("BloqueEn", x-1, y));
		pre.add(new Literal("HuecoEn", x-2, y));
		super.setPre(pre);
		
		ArrayList<Literal> add = new ArrayList<>();
		add.add(new Literal("EspacioLibre", x, y));
		add.add(new Literal("AvatarEnPosicion", x-1, y));
		add.add(new Literal("EspacioLibre", x-2, y));		
		super.setAdd(add);
	
		
		ArrayList<Literal> del = new ArrayList<>();
		del.add(new Literal("AvatarEnPosicion", x, y));
		del.add(new Literal("BloqueEn", x-1, y));
		del.add(new Literal("HuecoEn", x-2, y));
		super.setDel(del);

		ACTIONS accion = ACTIONS.ACTION_LEFT;
		super.setAccion(accion);
	}
		

}
