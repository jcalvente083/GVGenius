package si2025.jesusdavidcalvente083alu.p01.Antecedentes;

import si2025.jesusdavidcalvente083alu.p01.Mundo;
import si2025.jesusdavidcalvente083alu.p01.Cerebro.Condicion;

public class noEnColCentral implements Condicion{

	public noEnColCentral() {
		
	}
	
	@Override
	public boolean seCumple(Mundo m) {
		return (m.getPosAvatar()[0] != m.getnCol()/2);
	}
}
