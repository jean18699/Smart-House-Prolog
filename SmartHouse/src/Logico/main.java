package Logico;

import java.io.File;
import java.util.Map;

import org.jpl7.JPL;
import org.jpl7.Query;


public class main {

	

	
	
	public static void main(String[] args) {
		
		
 
		Query q = new Query("consult('src/Smart_house.pl')");
		System.out.println(q.hasSolution());  
	
		//q.nextSolution();
		
	/*	q = new Query("nuevo_miembro(pepe)");
		q.nextSolution();
		
		q = new Query("nuevo_miembro(martina)");
		q.nextSolution();
		
		
		q = new Query("dormir(pepe)");
		q.nextSolution();
		
		q = new Query("nuevo_electronico(nevera,65)");
		q.nextSolution();
		
		
		q = new Query("miembro(X)"); //ya puedo consultar hechos luego de realizar un consult
		q.nextSolution();
		
		q = new Query("consumo_total_activo(Total)");
		
		
		Map<String, Term>[] res = q.allSolutions();
		
		for(int i = 0; i < res.length; i++)
		{
			System.out.println(res[i].get("Total"));
		}
		*/
	}

}
