package si2025.jesusdavidcalvente083alu.p01.Antecedentes;

import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Condicion;

public class buenoEnPeligro implements Condicion{
	
	public buenoEnPeligro() {
		
	}

	@Override
	public boolean seCumple(Mundo m) {
		
		return m.getCayendoPeligro().size() > 0;
	}
	
}
