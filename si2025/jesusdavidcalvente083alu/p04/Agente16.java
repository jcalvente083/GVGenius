package si2025.jesusdavidcalvente083alu.p04;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p04.Cerebro.BEspacioEstados;
import tools.ElapsedCpuTimer;

public class Agente16 extends AbstractPlayer {

	private Mundo mundo = null;
	private BEspacioEstados cerebro = null;
	
	public Agente16(StateObservation so, ElapsedCpuTimer elapsedTimer) {
		
		mundo = new Mundo(so);
		cerebro = new BEspacioEstados(mundo);
	}
	
	
	@Override
	public ACTIONS act(StateObservation arg0, ElapsedCpuTimer arg1) {
		
		
		mundo.actualizar(arg0);	
		
		return cerebro.piensa(mundo);	
	}


}
