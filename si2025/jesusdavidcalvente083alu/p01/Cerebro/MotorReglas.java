package si2025.jesusdavidcalvente083alu.p01.Cerebro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import si2025.jesusdavidcalvente083alu.p01.Mundo;


public class MotorReglas implements Motor {
	
	private List<Regla> reglas;
	
	public MotorReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}
	
	public void guardarRegla(Regla regla) {
		String archivo = "C:\\Users\\Paulu\\Desktop\\CURSO2425\\SEGUNDO CUATRIMESTRE\\[SI] Sistemas Inteligentes\\Practicas\\SI_Practicas_JesusDavidCalventeZapata\\src\\SI2025\\jesusdavidcalvente083\\Practica3\\Main\\reglasRAW.csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
	
			writer.write(regla.getAccion().toString());
			writer.newLine();
	
		} catch (IOException e) {
			System.out.println("ERROR.- No se han podido guardar lso resultados :((");
		}
	}

	@Override
	public Regla disparo(Mundo m) {					
		for (int i = 0; i < reglas.size(); i++) {
			if (reglas.get(i).seCumple(m)) {
				
				guardarRegla(reglas.get(i));
					
				return reglas.get(i);			
			}
		}
		
		return null;								
	}
}
