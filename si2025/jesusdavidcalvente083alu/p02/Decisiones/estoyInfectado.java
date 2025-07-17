package si2025.jesusdavidcalvente083alu.p02.Decisiones;

import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Decision;

public class estoyInfectado implements Decision {
	
	public estoyInfectado() {
		
	}
	
	public boolean seCumple(Mundo m) {
		return m.estadoAvatar();
	}
	
	public String toString() {
		return "estoyInfectado";
	}

}
