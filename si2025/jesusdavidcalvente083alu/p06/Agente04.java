package si2025.jesusdavidcalvente083alu.p06;

import java.util.List;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;
import si2025.jesusdavidcalvente083alu.p06.Cerebro.*;



public class Agente04 extends AbstractPlayer{
	
	Mundo mundo;
	Busqueda cerebro;

	List<Operador> solucion;
	int contador = 0;
	
	public Agente04(StateObservation stateObs, ElapsedCpuTimer arg1) {
				
		mundo = new Mundo(stateObs);
		cerebro = new Busqueda(); 
		solucion = cerebro.piensa(mundo, true); 
		
		if(solucion == null) {
			System.out.println("No se ha encontrado una solucion");
			return;
		}
		
	
		

	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer arg1) {
		
		if(solucion!=null && contador < solucion.size())
		{
			Operador op = solucion.get(contador++);
			System.out.println(op);
			return op.getAccion();
			
		}
		return ACTIONS.ACTION_NIL;
	}


}
