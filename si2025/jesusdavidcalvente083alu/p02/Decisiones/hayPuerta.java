package si2025.jesusdavidcalvente083alu.p02.Decisiones;

import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Decision;

public class hayPuerta implements Decision {
	
	public hayPuerta() {

	}

	@Override
	public boolean seCumple(Mundo m) {
		return m.hayPuertas();
	}
	
	@Override
	public String toString() {
		return "hayPuerta";
	}
	
}
