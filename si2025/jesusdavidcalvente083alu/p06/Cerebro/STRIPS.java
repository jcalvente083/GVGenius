package si2025.jesusdavidcalvente083alu.p06.Cerebro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

import si2025.jesusdavidcalvente083alu.p06.Mundo;

public class STRIPS implements Estado {

	private Stack<Object> pila;
	private ArrayList<Operador> plan;
	private ArrayList<Literal> estadoActual;
	private ArrayList<Operador> operadores;
	private ArrayList<Literal> metas;

	public STRIPS(Mundo m) {

		pila = new Stack<>();
		plan = new ArrayList<>();
		operadores = m.obtenerOperadores();

		estadoActual = m.getEstadoInicial();

		metas = m.getMetas();
		
		System.out.println(metas);

		if (metas.size() > 1) {

			pila.push(new Multiliteral(metas));

		} else if (metas.size() == 1) {

			pila.push(metas.get(0));

		}
	}

	public ArrayList<Operador> getPlan() {
		return plan;
	}

	@Override
	public boolean esMeta() {
		return pila.isEmpty();
	}

	public STRIPS(Stack<Object> pila, ArrayList<Literal> estadoActual, ArrayList<Operador> plan,
			ArrayList<Operador> operadores, ArrayList<Literal> metas) {

		this.pila = (Stack<Object>) pila.clone();
		this.estadoActual = new ArrayList<>(estadoActual);
		this.plan = new ArrayList<>(plan);
		this.operadores = operadores;
		
		this.metas = metas;
	}

	private STRIPS copiar() {
		return new STRIPS(pila, estadoActual, plan, operadores, metas);
	}

	@Override
	public ArrayList<Estado> getSucesores() {
		ArrayList<Estado> sucesores = new ArrayList<>();

		if (pila.isEmpty())
			return sucesores;

		Object top = pila.peek();

		if (top instanceof Operador) {
			Operador op = (Operador) top;

			if (op.esAplicable(estadoActual)) {
				STRIPS hijo = copiar();
				hijo.pila.pop();
				hijo.estadoActual.removeAll(op.getDel());
				hijo.estadoActual.addAll(op.getAdd());
				hijo.plan.add(op);
				sucesores.add(hijo);
			} else {
				STRIPS hijo = copiar();

				hijo.pila.push(new Multiliteral(op.getPre()));
				sucesores.add(hijo);
			}
		} else if (top instanceof Multiliteral) {
			Multiliteral ml = (Multiliteral) top;
			ArrayList<Literal> lits = ml.getLiterales();

			if (estadoActual.containsAll(lits)) {
				STRIPS hijo = copiar();
				hijo.pila.pop();
				sucesores.add(hijo);
			} else {
				STRIPS hijo = copiar();
				for (Literal lit : lits) {
					hijo.pila.push(lit);
					if (pila.contains(lit)) {
						return sucesores; // Evita duplicados
					}
				}
				sucesores.add(hijo);
			}
		} else if (top instanceof Literal) {
			Literal lit = (Literal) top;

			if (estadoActual.contains(lit)) {
				STRIPS hijo = copiar();
				hijo.pila.pop();
				sucesores.add(hijo);
			} else {

				for (Operador op : operadores) {
					if (op.getAdd().contains(lit)) {
						STRIPS hijo = copiar();
						hijo.pila.push(op);
						sucesores.add(hijo);
					}
				}

			}
		}

		return sucesores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estadoActual, pila, plan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		STRIPS other = (STRIPS) obj;
		return Objects.equals(estadoActual, other.estadoActual) && Objects.equals(pila, other.pila)
				&& Objects.equals(plan, other.plan);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pila:\n");
		for (int i = pila.size() - 1; i >= 0; i--) {
			sb.append("  ").append(pila.get(i)).append("\n");
		}
		sb.append("Estado actual: ").append(estadoActual).append("\n");

		return sb.toString();
	}

}
