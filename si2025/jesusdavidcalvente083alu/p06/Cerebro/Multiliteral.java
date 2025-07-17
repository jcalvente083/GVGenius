package si2025.jesusdavidcalvente083alu.p06.Cerebro;

import java.util.List;
import java.util.ArrayList;

public class Multiliteral {
	
	private ArrayList<Literal> literales;
	
	public Multiliteral() {
		this.literales = new ArrayList<>();
	}
	
	public Multiliteral(ArrayList<Literal> literales) {
		this.literales = (ArrayList<Literal>) literales.clone();
	}
	
	public Multiliteral(List<Literal> literales) {
		this.literales = new ArrayList<>(literales);
	}
	
	public void addLiteral(Literal literal) {
		literales.add(literal);
	}
	
	public ArrayList<Literal> getLiterales() {
		return literales;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("(Multiliteral) [ ");
		
		for (Literal literal : literales) {
			sb.append(literal.toString()).append(" ");
		}
		
		sb.append("]");
		
		return sb.toString();
	}

}
