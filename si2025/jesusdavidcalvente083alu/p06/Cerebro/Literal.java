package si2025.jesusdavidcalvente083alu.p06.Cerebro;

import java.util.Objects;

public class Literal {
	
	private String nombre;
	private int x, y;
	
	public Literal(String nombre, int x, int y) {
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(Literal) [ " + nombre + "( " + x + ", " + y + ") ]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Literal other = (Literal) obj;
		return x == other.x && y == other.y && Objects.equals(nombre, other.nombre);
	}

}
