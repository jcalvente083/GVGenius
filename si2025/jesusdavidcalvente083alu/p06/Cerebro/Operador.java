package si2025.jesusdavidcalvente083alu.p06.Cerebro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ontology.Types.ACTIONS;

public abstract class Operador {
	
	private String nombre;
	
	private ArrayList<Literal> pre;
	private ArrayList<Literal> add;
	private ArrayList<Literal> del;
	
	private int x, y;
	
	public ACTIONS accion;
	
	
	public Operador() {
		this.nombre = "";
		this.pre = new ArrayList<>();
		this.add = new ArrayList<>();
		this.del = new ArrayList<>();
		this.accion = ACTIONS.ACTION_NIL;
		this.x = -1;
		this.y = -1;
	}
	
	public Operador(String nombre) {
		this.nombre = nombre;
		this.pre = new ArrayList<>();
		this.add = new ArrayList<>();
		this.del = new ArrayList<>();
		this.accion = ACTIONS.ACTION_NIL;
		this.x = -1;
		this.y = -1;
	}
	
	public Operador(String nombre, int x, int y) {
		this.nombre = nombre;
		this.pre = new ArrayList<>();
		this.add = new ArrayList<>();
		this.del = new ArrayList<>();
		this.accion = ACTIONS.ACTION_NIL;
		this.x = x;
		this.y = y;
	}
	
	public Operador(String nombre, ArrayList<Literal> pre, ArrayList<Literal> add, ArrayList<Literal> del, ACTIONS accion, int x, int y) {
		this.nombre = nombre;
		this.pre = pre;
		this.add = add;
		this.del = del;
		this.accion = accion;
		this.x = x;
		this.y = y;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Literal> getPre() {
		return pre;
	}

	public ArrayList<Literal> getAdd() {
		return add;
	}
	
	public ArrayList<Literal> getDel() {
		return del;
	}
	
	public ACTIONS getAccion() {
		return this.accion;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
		
	public void setAccion(ACTIONS accion) {
		this.accion = accion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPre(ArrayList<Literal> pre) {
		this.pre = pre;
	}
	
	public void setAdd(ArrayList<Literal> add) {
		this.add = add;
	}
	
	public void setDel(ArrayList<Literal> del) {
		this.del = del;
	}
	
	
	public boolean esAplicable(List<Literal> estado) {
		
		return estado.containsAll(pre); 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(add, pre, del, accion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operador other = (Operador) obj;
		return other.getX() == this.x && other.getY() == this.y && Objects.equals(nombre, other.nombre) && other.getAccion() == this.accion;
	}

	
	@Override
	public String toString() {
		return nombre + "( " + x + ", " + y + " )";
	
	
	
	}
}
