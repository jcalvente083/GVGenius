package si2025.jesusdavidcalvente083alu.p06.Operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;

public class CogerLlave extends Operador {

	public CogerLlave(int x, int y) {
	
			super("CogerLlave", x, y);
			
			ArrayList<Literal> pre = new ArrayList<>();
			pre.add(new Literal("AvatarEnPosicion", x, y));   
			pre.add(new Literal("LlaveEn", x, y));
			super.setPre(pre);
			
			ArrayList<Literal> add = new ArrayList<>();          
			add.add(new Literal("LlaveCogida", -1, -1));        
			super.setAdd(add);
			
			ArrayList<Literal> del = new ArrayList<>();         
			del.add(new Literal("LlaveEn", x, y ));          
     
			super.setDel(del);
			
			ACTIONS accion = ACTIONS.ACTION_NIL;
			super.setAccion(accion);
		super.setAccion(accion);
		
	}

}
