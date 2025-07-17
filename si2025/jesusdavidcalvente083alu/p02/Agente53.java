package si2025.jesusdavidcalvente083alu.p02;


import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Motor;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.MotorArbol;
import tools.ElapsedCpuTimer;

public class Agente53 extends AbstractPlayer {

	private Mundo mundo = null;
	private Motor cerebro = null;


	public Agente53(StateObservation stObs, ElapsedCpuTimer elapsedTimer) { 

		mundo = new Mundo(stObs);
		cerebro = new MotorArbol(mundo);
	
	}

	@Override
	public ACTIONS act(StateObservation stObs, ElapsedCpuTimer arg1) {
		// Actualizamos el mundo con la nueva observacion
		mundo.actualizar(stObs);

		return cerebro.piensa(mundo);

		
	}

}
