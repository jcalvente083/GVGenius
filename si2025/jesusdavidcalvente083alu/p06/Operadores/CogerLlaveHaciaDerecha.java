package si2025.jesusdavidcalvente083alu.p06.Operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;

public class CogerLlaveHaciaDerecha extends Operador {

	public CogerLlaveHaciaDerecha(int x, int y) {
		super("CogerLlaveHaciaIzquierda", x, y);

		ArrayList<Literal> pre = new ArrayList<>();
		pre.add(new Literal("AvatarEnPosicion", x, y));
		pre.add(new Literal("LlaveEn", x + 1, y));
		super.setPre(pre);

		ArrayList<Literal> add = new ArrayList<>();
		add.add(new Literal("EspacioLibre", x, y));                
		add.add(new Literal("AvatarEnPosicion", x + 1, y));      
		add.add(new Literal("LlaveCogida", -1, -1));              
		super.setAdd(add);

		ArrayList<Literal> del = new ArrayList<>();
		del.add(new Literal("LlaveEn", x + 1, y));                
		del.add(new Literal("AvatarEnPosicion", x, y));
		del.add(new Literal("EspacioLibre", x + 1, y));
		super.setDel(del);

		ACTIONS accion = ACTIONS.ACTION_RIGHT;
		super.setAccion(accion);
		
	}

}
