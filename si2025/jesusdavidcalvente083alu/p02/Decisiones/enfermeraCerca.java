package si2025.jesusdavidcalvente083alu.p02.Decisiones;

import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Cerebro.Decision;
import tools.Vector2d;

public class enfermeraCerca implements Decision {

	public enfermeraCerca() {

	}

	public boolean seCumple(Mundo m) {
		if (m.hayEnfermeras()) {

			Vector2d enfermera = m.getCapturar();
			Vector2d orientacion = m.getOrientacion();
			int[] avatar = m.getAvatar();

			enfermera.x = (int) (enfermera.x);
			enfermera.y = (int) (enfermera.y);
			
			orientacion.x = (int) orientacion.x;
			orientacion.y = (int) orientacion.y;
			

			return avatar[0] + orientacion.x == enfermera.x && avatar[1] + orientacion.y == enfermera.y;
			
		} else {
			return false;
		}

	}

	public String toString() {
		return "enfermeraCerca";
	}

}
