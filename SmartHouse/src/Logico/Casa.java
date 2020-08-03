package Logico;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;



public class Casa {

	Query q;
	ArrayList<MiembroFamilia> miembros = new ArrayList<MiembroFamilia>();
	ArrayList<String> puertas = new ArrayList<String>();
	
	Thread dormirPersonas = new Thread(() -> {
        try {
			dormir();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    });
	
	Thread despertarPersonas = new Thread(() -> {
        try {
			despertar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    });
	
	public Casa()
	{
		q = new Query("consult('src/Smart_house.pl')");
		q.nextSolution();
		
		
		
	}
	
	
	public void add_miembro(String nombre)
	{
		String queryText = String.format("nuevo_miembro(%s)", nombre);
		q = new Query(queryText);	
		
		//Si fue posible agregar este nuevo miembro, agregalo a el arreglo de miembros declarado en esta clase
		if(q.hasSolution())
		{
			MiembroFamilia miembro = new MiembroFamilia(nombre);
			miembros.add(miembro);		
		}
		if(miembros.size() == 1)
		{
			//dormirPersonas.start();
			//despertarPersonas.start();
		}
		//Si se agrego el primer miembro, comienzo a iniciar los threads
		
	}
	
	public void getNombreMiembros(DefaultListModel<String> modelListMiembros)
	{
		modelListMiembros.clear();
	
		for(int i = 0; i < miembros.size(); i++)
		{
			modelListMiembros.add(i, miembros.get(i).getNombre());
			
		
		}
	}
	
	public synchronized MiembroFamilia getMiembro(String nombre)
	{
	
	
		for(int i = 0; i < miembros.size(); i++)
		{
			if(miembros.get(i).getNombre().equals(nombre))
			{
				return miembros.get(i);
			}
			
		}
		return null;
	}
	
	public void dormir() throws InterruptedException
	{
		String queryText;
		Random rand = new Random();
		int index;
		String persona;
		/*do
		{*/
		
			if(!miembros.isEmpty()) {

				//Thread.sleep(5000);
				index = rand.nextInt(miembros.size());
				
				persona = miembros.get(index).getNombre();
				
				
				queryText = String.format("dormir(%s)", persona);
				q = new Query(queryText);
				
				
				if(q.hasSolution())
				{
					
					getMiembro(persona).setDormido(true);
					
				}

			}
		/*}while(true);*/
				
	}
	
	public void despertar() throws InterruptedException
	{
		String queryText;
		Random rand = new Random();
		int index;
		String persona;
		/*do
		{*/
		
			if(!miembros.isEmpty()) {

				//Thread.sleep(10000);
				index = rand.nextInt(miembros.size());
				
				persona = miembros.get(index).getNombre();
				
				
				queryText = String.format("despertar(%s)", persona);
				q = new Query(queryText);
				
				
				if(q.hasSolution())
				{
					
					getMiembro(persona).setDormido(false);
					
					
				}

			}
		/*}while(true);*/
				
	}
	
	public int getTotalDormidos()
	{
		int total = 0;
		for(int i = 0; i < miembros.size(); i++) {
			if(miembros.get(i).getDormido())
			{
				total++;
			}
		}
		return total;
	}
	
	public void addElectronico(String nombre, int consumo)
	{
		String queryText = String.format("nuevo_electronico(%s, %d)", nombre,consumo);
		q = new Query(queryText);	
		
		q.next();
		
	}
	
	public void getElectronicos()
	{
		String queryText = String.format("electronico(X,Y)");
		q = new Query(queryText);	
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{
			
			System.out.println(res[i].get("X"));
			System.out.println(res[i].get("Y"));
		}
		
		
	}
	
	public void addPuerta(String nombre)
	{
		String queryText = String.format("nueva_puerta(%s)", nombre);
		q = new Query(queryText);	
		q.next();
		
	}
	
	public void getPuertas(DefaultListModel<String> modelListPuertas)
	{
		String queryText = String.format("puerta(X)");
		q = new Query(queryText);	
		
		puertas.clear();
		modelListPuertas.clear();
		System.out.println(q.hasSolution());
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{
			System.out.println(res[i].get("X").toString());
			puertas.add(res[i].get("X").toString());
			modelListPuertas.add(i, res[i].get("X").toString());
		}
		
		
	}
	
	//public void getCerrada()
	
}
