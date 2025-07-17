package si2025.jesusdavidcalvente083alu.p06.Cerebro;

import java.util.List;

public interface Estado {
	
	public List<Estado> getSucesores();
	public boolean esMeta();
	public List<Operador> getPlan();
	

}
