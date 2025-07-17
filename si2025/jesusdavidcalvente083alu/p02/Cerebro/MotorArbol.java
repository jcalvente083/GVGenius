package si2025.jesusdavidcalvente083alu.p02.Cerebro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ontology.Types.ACTIONS;
import si2025.jesusdavidcalvente083alu.p02.Mundo;
import si2025.jesusdavidcalvente083alu.p02.Decisiones.*;
import si2025.jesusdavidcalvente083alu.p02.Acciones.*;

public class MotorArbol implements Motor {

	private NodoDecision hayPuerta = null; // Se cumple si hay alguna puerta en el mapa.
	private NodoDecision enfermeraCerca = null; // Se cumple si hay alguna enfermera cerca del avatar. Cerca = bloque
												// contiguo
	private NodoDecision hayEnfermeras = null; // Se cumple si hay alguna enfermera en el mapa.
	private NodoDecision estoyInfectado = null;// Se cumple si el avatar esta infectado.

	private NodoAccion matarEnfermera = null;
	private NodoAccion acercarEnfermera = null;
	private NodoAccion quieto = null;
	private NodoAccion infectarNPC = null;
	private NodoAccion infectarPlayer = null;
	

	public MotorArbol(Mundo m) {
		// Nodos de Decision
		hayPuerta = new NodoDecision(new hayPuerta());
		enfermeraCerca = new NodoDecision(new enfermeraCerca());
		hayEnfermeras = new NodoDecision(new hayEnfermera());
		estoyInfectado = new NodoDecision(new estoyInfectado());

		// Nodos de Accion
		matarEnfermera = new NodoAccion(new MatarEnfermera(m));
		acercarEnfermera = new NodoAccion(new AcercarEnfermera(m));
		quieto = new NodoAccion(new Quieto(m));
		infectarNPC = new NodoAccion(new InfectarNPC(m));
		infectarPlayer = new NodoAccion(new InfectarPlayer(m));

		
		// Arbol de decisiones
		
		/*
		 
		 		                 _______hayEnfermeras_______
		 		             	/		  			        \ 	   
		 		               /          			         \
		 		              /          				      \
		 		       enfermeraCerca                       hayPuerta
		 				/             \                    /        \
		 			   /               \                  /          \
		       *matarEnfermera* *acercarEnfermera*  *quieto*   estoyInfectado
		 			                              				/        \
		 			                             		   	   /          \
		 			            					    *infectarNPC*  *infectarPlayer*	
		 
		 */

		
		
		
		hayEnfermeras.setIzq(enfermeraCerca);
		hayEnfermeras.setDer(hayPuerta);
		
		enfermeraCerca.setIzq(matarEnfermera);
		enfermeraCerca.setDer(acercarEnfermera);
		
		hayPuerta.setIzq(quieto);
		hayPuerta.setDer(estoyInfectado);
		
		estoyInfectado.setIzq(infectarNPC);
		estoyInfectado.setDer(infectarPlayer);
	
		
		 
		 /*
		hayPuerta.setIzq(hayEnfermeras);
		hayPuerta.setDer(estoyInfectado);
		
		hayEnfermeras.setIzq(enfermeraCerca);
		hayEnfermeras.setDer(estoyInfectado);
		
		enfermeraCerca.setIzq(matarEnfermera);
		enfermeraCerca.setDer(acercarEnfermera);
		
		estoyInfectado.setIzq(infectarNPC);
		estoyInfectado.setDer(infectarPlayer);
		*/
		
		
		
	}
	
	public void guardarAccion(NodoAccion accion) {
		String archivo =  "C:\\Users\\Paulu\\Desktop\\CURSO2425\\SEGUNDO CUATRIMESTRE\\[SI] Sistemas Inteligentes\\prueba\\si2025\\src\\si2025\\jesusdavidcalvente083alu\\p02\\accionesRAW.csv";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
	
			writer.write(accion.getAccion().toString());
			writer.newLine();
	
		} catch (IOException e) {
			System.out.println("ERROR.- No se han podido guardar lso resultados :((");
		}
	}

	@Override
	public ACTIONS piensa(Mundo m) {
		
		NodoAccion accion = (NodoAccion) (hayEnfermeras.decide(m));
		//System.out.println("Accion elegida: " + accion.getAccion().toString());
		guardarAccion(accion);
		return accion.doAction(m);

	}

}
