package si2025.jesusdavidcalvente083alu.p01;

import java.util.ArrayList;
import java.util.List;

import si2025.jesusdavidcalvente083alu.p01.Cerebro.*;
import si2025.jesusdavidcalvente083alu.p01.Acciones.*;
import si2025.jesusdavidcalvente083alu.p01.Antecedentes.*;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public class Agente89 extends AbstractPlayer{
	
	private Mundo mundo = null;
	private MotorReglas motor = null;


public Agente89(StateObservation stObs, ElapsedCpuTimer elapsedTimer) {
	
		mundo = new Mundo(stObs);
		
		Condicion condicionPeligro = new buenoEnPeligro();
		Condicion condicionMochilaLlena = new mochilaLlena();
		Condicion condicionHayEnemigos = new hayEnemigos();
		Condicion condicionCentro = new noEnColCentral();
		Condicion condicionSiempreCierta = new siempreCierta();
		
		Accion accionSalvarBuenos = new salvarBueno(mundo);
		Accion accionVaciarBolsa = new vaciarBolsa(mundo);
		Accion accionAtacarEnemigos = new matarMalo(mundo);
		Accion accionIrCentro = new irCentro(mundo);
		Accion accionHacerNada = new irCentro(mundo);
		
		List<Condicion> listaPersonaEnPeligro = new ArrayList<Condicion>(); listaPersonaEnPeligro.add(condicionPeligro);
		List<Condicion> listaMochilaLlena = new ArrayList<Condicion>(); listaMochilaLlena.add(condicionMochilaLlena);
		List<Condicion> listaHayEnemigos = new ArrayList<Condicion>(); listaHayEnemigos.add(condicionHayEnemigos);
		List<Condicion> listaCentro = new ArrayList<Condicion>(); listaCentro.add(condicionCentro);
		List<Condicion> listaSiempreCierta = new ArrayList<Condicion>(); listaSiempreCierta.add(condicionSiempreCierta);
				
		Regla reglaPersonaEnPeligro = new Regla(listaPersonaEnPeligro, accionSalvarBuenos);
		Regla reglaVaciarBolsa = new Regla(listaMochilaLlena, accionVaciarBolsa);
		Regla reglaHayEnemigos = new Regla(listaHayEnemigos, accionAtacarEnemigos);
		Regla reglaIrCentro = new Regla(listaCentro, accionIrCentro);
		Regla reglaCierta = new Regla(listaSiempreCierta, accionHacerNada);
		
		
	
		ArrayList<Regla> listaReglas = new ArrayList<Regla>();

		listaReglas.add(reglaPersonaEnPeligro);
		listaReglas.add(reglaVaciarBolsa);
		listaReglas.add(reglaHayEnemigos);
		//listaReglas.add(reglaIrCentro);
		listaReglas.add(reglaCierta);
	
		
		motor = new MotorReglas(listaReglas);

		

	}
 



	@Override
	public ACTIONS act(StateObservation stObs, ElapsedCpuTimer arg1) {
		
		// VER
		mundo.actualizar(stObs);	
		
		// PENSAR
		Regla r = motor.disparo(mundo);
		
		// ACTUAR
		return r.getAccion().doAction(mundo);	

	}


}

