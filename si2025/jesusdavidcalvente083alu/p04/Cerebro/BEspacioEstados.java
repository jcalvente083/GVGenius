package si2025.jesusdavidcalvente083alu.p04.Cerebro;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import ontology.Types.ACTIONS;
import tools.Vector2d;
import si2025.jesusdavidcalvente083alu.p04.Mundo;
import si2025.jesusdavidcalvente083alu.p04.ElementosBusqueda.*;
import si2025.jesusdavidcalvente083alu.p04.Operadores.*;

public class BEspacioEstados extends Motor {

	private Operador abajo = new Abajo();
	private Operador arriba = new Arriba();
	private Operador derecha = new Derecha();
	private Operador izquierda = new Izquierda();
	private Operador nil = new NIL();
	private List<ACTIONS> acciones;

	public BEspacioEstados(Mundo m) {
		acciones = Busqueda(m);
	}

	@Override
	public ACTIONS piensa(Mundo m) {

		ACTIONS a = acciones.get(0);

		acciones.remove(0);

		return a;

	}

	public List<ACTIONS> Busqueda(Estado inicial) {

		List<Estado> Abiertos = new LinkedList<>();
		List<Estado> Cerrados = new LinkedList<>();

		Abiertos.add(inicial);

		while (Abiertos.size() > 0) {

			Abiertos.sort(new Comparator<Estado>() {
				public int compare(Estado e1, Estado e2) {

					return ((Mundo) e1).compararEstado(e2);

				}
			});
			Estado actual = Abiertos.get(0);
			Abiertos.remove(0);
			Cerrados.add(actual);

			if (actual.isMeta()) {
				System.out.println("META");
				return Recuperar_Camino(inicial, actual);
			} else {

				List<Estado> sucesores = getSucesores(actual);
				sucesores = QuitarRepetidos(sucesores, Cerrados);
				Abiertos.addAll(sucesores);

			}
		}

		return null;

	}

	private List<Estado> QuitarRepetidos(List<Estado> sucesores, List<Estado> cerrados) {

		List<Estado> sucesoresSinRepeticion = new LinkedList<>();
		for (Estado e : sucesores) {
			if (!cerrados.contains(e)) {
				sucesoresSinRepeticion.add(e);
			}

		}
		return sucesoresSinRepeticion;
	}

	private List<ACTIONS> Recuperar_Camino(Estado inicial, Estado actual) {
		List<ACTIONS> acciones = new LinkedList<>();
		Estado tmp = actual;
		while (((Mundo) tmp).getAnterior() != null) {

			if (((Mundo) tmp).getOperador() != null) {

				Vector2d posAnterior = ((Mundo) ((Mundo) tmp).getAnterior()).getAvatar();
				Vector2d posActual = ((Mundo) tmp).getAvatar();
				int diferencia = calcularDistancia(posActual, posAnterior);
				if (diferencia > 1 ) {
					for (int i = 0; i <= 2 + diferencia + ((Mundo)((Mundo) tmp).getAnterior()).getFlechaFlag(); i++) {
					
						acciones.add(nil.getAccion());
					}

				}

				acciones.add(((Mundo) ((Mundo) tmp)).getOperador().getAccion());
			}

			tmp = ((Mundo) tmp).getAnterior();

		}

		Collections.reverse(acciones);

		return acciones;
	}

	private List<Estado> getSucesores(Estado actual) {
		List<Estado> sucesores = new LinkedList<>();

		sucesores.add(actual.aplicarOperador(abajo));
		sucesores.add(actual.aplicarOperador(arriba));
		sucesores.add(actual.aplicarOperador(derecha));
		sucesores.add(actual.aplicarOperador(izquierda));

		return sucesores;
	}

	private int calcularDistancia(Vector2d posActual, Vector2d posAnterior) {

		int vx = (int) Math.abs(posActual.x - posAnterior.x);
		int vy = (int) Math.abs(posActual.y - posAnterior.y);


		return Math.abs(vx + vy);

	}

}
