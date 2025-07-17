package si2025.jesusdavidcalvente083alu.p06.Operadores;

import java.util.ArrayList;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Literal;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.Operador;

public class MoverPiedraArriba extends Operador {

	public MoverPiedraArriba(int x, int y) {
		
		super("MoverPiedraArriba", x, y);
		
		ArrayList<Literal> Precondicion=new ArrayList<Literal>();
		Precondicion.add(new Literal("AvatarEnPosicion", x, y));
		Precondicion.add(new Literal("EspacioLibre", x, y-2));
		Precondicion.add(new Literal("BloqueEn", x, y-1));
		super.setPre(Precondicion);
		
		ArrayList<Literal> Adicion=new ArrayList<Literal>();
		Adicion.add(new Literal("EspacioLibre", x, y));
		Adicion.add(new Literal("AvatarEnPosicion", x, y-1));
		Adicion.add(new Literal("BloqueEn", x, y-2));
		super.setAdd(Adicion);
		
		ArrayList<Literal> Supresion=new ArrayList<Literal>();
		Supresion.add(new Literal("AvatarEnPosicion", x, y));
		Supresion.add(new Literal("BloqueEn", x, y-1));
		Supresion.add(new Literal("EspacioLibre", x, y-2));
		super.setDel(Supresion);
		
		ACTIONS accion = ACTIONS.ACTION_UP;
		super.setAccion(accion);
		
		
	}


}
