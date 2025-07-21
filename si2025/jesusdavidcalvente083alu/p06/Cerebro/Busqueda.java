package si2025.jesusdavidcalvente083alu.p06.Cerebro;

import java.util.List;
import java.util.ArrayList;

import si2025.jesusdavidcalvente083alu.p06.Mundo;

public class Busqueda {

	public List<Operador> piensa(Mundo m, boolean profundidad) {

		List<Estado> Cerrados = new ArrayList<>();

		List<Estado> Abiertos = new ArrayList<>();

		List<Estado> Sucesores;

		Estado actual;

		Abiertos.add(new STRIPS(m));

		while (!Abiertos.isEmpty()) {

			actual = Abiertos.get(0);
			Abiertos.remove(0);

			//System.out.println(actual);
			
			Cerrados.add(actual);

			if (actual.esMeta()) {
			
				return actual.getPlan();
				
			} else {
				Sucesores = actual.getSucesores();
				//System.out.println("Sucesores: " + Sucesores.size());

				if (profundidad) {
					Abiertos.addAll(0, Sucesores);

				} else { // ANCHURA
					Abiertos.addAll(Sucesores);

				}

			}

		}

		return null; // No se ha encontrado un plan
	}

}
