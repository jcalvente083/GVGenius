package si2025.jesusdavidcalvente083alu.p06.Operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;

public class MoverPiedraDerecha extends Operador {
	
	public MoverPiedraDerecha(int x, int y) {
		
		super("MoverPiedraDerecha", x, y);

		ArrayList<Literal> Precondicion=new ArrayList<Literal>();
		Precondicion.add(new Literal("AvatarEnPosicion", x, y));
		Precondicion.add(new Literal("EspacioLibre", x+2, y));
		Precondicion.add(new Literal("BloqueEn", x+1, y));
		super.setPre(Precondicion);
		
		ArrayList<Literal> Adicion=new ArrayList<Literal>();
		Adicion.add(new Literal("EspacioLibre", x, y));
		Adicion.add(new Literal("AvatarEnPosicion", x+1, y));
		Adicion.add(new Literal("BloqueEn", x+2, y));
		super.setAdd(Adicion);
		
		ArrayList<Literal> Supresion=new ArrayList<Literal>();
		Supresion.add(new Literal("AvatarEnPosicion", x, y));
		Supresion.add(new Literal("BloqueEn", x+1, y));
		Supresion.add(new Literal("EspacioLibre", x+2, y));
		super.setDel(Supresion);
		
		ACTIONS accion = ACTIONS.ACTION_RIGHT;
		super.setAccion(accion);
		
	}

	

}
