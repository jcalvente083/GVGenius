package si2025.jesusdavidcalvente083alu.p02.Decisiones;

import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Decision;

public class hayEnfermera implements Decision {
	
	public hayEnfermera() {
		
	}
	
	public boolean seCumple(Mundo m) {
		return m.hayEnfermeras();
	}
	
	public String toString() {
		return "hayEnfermera";
	}


}
