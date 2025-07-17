package si2025.jesusdavidcalvente083alu.p01.Antecedentes;

import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Condicion;

public class hayEnemigos implements Condicion{
	
	public hayEnemigos() {
	}


	@Override
	public boolean seCumple(Mundo m) {
		return  (m.getTicsJuego() > 950 || m.getBuenos().size() == 0) && m.getMalos().size() > 0;
	}


}
