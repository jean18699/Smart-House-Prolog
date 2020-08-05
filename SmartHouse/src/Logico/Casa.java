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
	
	/*Thread dormirPersonas = new Thread(() -> {
        try {
			dormir(null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    });
	*/
	/*Thread despertarPersonas = new Thread(() -> {
        try {
			despertar(null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    });*/
	
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
	
	public void getMiembros(DefaultListModel<String> modelListMiembros)
	{
		modelListMiembros.clear();
	
		String queryText = String.format("miembro(X)");
	
		q = new Query(queryText);
		
		Map<String, Term>[] res = q.allSolutions();
		
		for(int i = 0; i < res.length; i++)
		{
			modelListMiembros.add(i, res[i].get("X").toString());
		}
		
	}
	
	public void getElectronicos(DefaultListModel<String> modelListElectronicos)
	{
		modelListElectronicos.clear();
	
		String queryText = String.format("electronico(X)");
	
		q = new Query(queryText);
		
		Map<String, Term>[] res = q.allSolutions();
		
		for(int i = 0; i < res.length; i++)
		{
			modelListElectronicos.add(i, res[i].get("X").toString());
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
	
	public boolean dormir(String nombre) /*throws InterruptedException*/
	{
	//	String queryText;
		//Random rand = new Random();
		//int index;
	//	String persona;
		/*do
		{*/
		
		/*	if(!miembros.isEmpty()) {

				//Thread.sleep(5000);
				index = rand.nextInt(miembros.size());
				
				persona = miembros.get(index).getNombre();
				
		*/		
				String queryText = String.format("dormir(%s)", nombre);
				q = new Query(queryText);
				
				
				if(q.hasSolution())
				{					
					return true;
				}else
				{
					return false;
				}

		//	}
		/*}while(true);*/
				
	}
	
	public boolean isDormido(String nombre)
	{
		String queryText = String.format("dormido(%s)", nombre);
		q = new Query(queryText);
		
		if(q.hasSolution())
		{
			return true;
		}
		
		return false;
	}
	
	
	public boolean despertar(String nombre) /*throws InterruptedException*/
	{
		//String queryText;
		//Random rand = new Random();
		//int index;
		//String persona;
		/*do
		{*/
		
			/*if(!miembros.isEmpty()) {

				//Thread.sleep(10000);
				index = rand.nextInt(miembros.size());
				
				persona = miembros.get(index).getNombre();
				
				*/
				String queryText = String.format("despertar(%s)", nombre);
				q = new Query(queryText);
				
			/*	
				*/
				if(q.hasSolution())
				{					
					return true;
				}else
				{
					return false;
				}

			//}
		/*}while(true);*/
				
	}
	
	/*public boolean todosDormidos()
	{
		
		String queryText = String.format("cerrar_puertas_automaticamente()");
		q = new Query(queryText);
		
	/*	
		
		if(q.hasSolution())
		{					
			return true;
		}else
		{
			return false;
		}
		
	}
	*/
	public boolean salir(String nombre)
	{
		
		String queryText = String.format("salir(%s)",nombre);
		q = new Query(queryText);
		

		if(q.hasSolution())
		{					
			return true;
		}else
		{
			return false;
		}
		
	}
	
	public boolean volver(String nombre)
	{
		
		String queryText = String.format("volver(%s)",nombre);
		q = new Query(queryText);
		
	
		if(q.hasSolution())
		{					
			return true;
		}else
		{
			return false;
		}
		
	}
	
	public boolean setPuertaAutomatico(String nombre)
	{
		String queryText = String.format("modo_automatico_puerta(%s)",nombre);
		q = new Query(queryText);
		
	
		if(q.hasSolution())
		{		
			q.next();
			return true;
		
		}else
		{
			return false;
		}
	}
	
	public boolean setPuertaManual(String nombre)
	{
		String queryText = String.format("modo_manual_puerta(%s)",nombre);
		q = new Query(queryText);
		
	
		if(q.hasSolution())
		{					
			return true;
		}else
		{
			return false;
		}
	}
	
	
	public boolean isManual(String nombre)
	{
		String queryText = String.format("manual(%s)",nombre);
		q = new Query(queryText);
		
	
		if(q.hasSolution())
		{				
			
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean isAutomatico(String nombre)
	{
		String queryText = String.format("automatico(%s)",nombre);
		q = new Query(queryText);
		
	
		if(q.hasSolution())
		{					
			return true;
		}else
		{
			return false;
		}
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
	
	
	
	
	public boolean addElectronico(String nombre, int consumo)
	{
		String queryText = String.format("nuevo_electronico(%s, %d)", nombre,consumo);
		q = new Query(queryText);	
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
		
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
	
	public int getConsumoElectronico(String nombre)
	{
		String queryText = String.format("consumo(%s,X)",nombre);
		q = new Query(queryText);	
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("X").intValue();
		
	}
	
	public int getConsumoElectronicoTotal()
	{
		String queryText = String.format("get_total_consumo(Total)");
		q = new Query(queryText);	
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").intValue();

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
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{
			
			puertas.add(res[i].get("X").toString());
			modelListPuertas.add(i, res[i].get("X").toString());
		}
		
		
	}


	public boolean isFuera(String miembro) {
		String queryText = String.format("salio(%s)", miembro);
		q = new Query(queryText);
		
		if(q.hasSolution())
		{
			return true;
		}
		
		return false;
		
	}
	
	public boolean nuevoQuery(String query,String arg)
	{
		String queryText = String.format(query+"(%s)", arg);
		System.out.println(queryText);
		q = new Query(queryText);
		System.out.println(q.hasSolution());
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
	}
	//public void getCerrada()


	public void getZonas(DefaultListModel<String> modelListaZonas) {
		
		String queryText = String.format("zonaCasa(X)");
		q = new Query(queryText);	
		
		modelListaZonas.clear();
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{
			System.out.println(modelListaZonas);
			modelListaZonas.add(i, res[i].get("X").toString());
		}
		
	}


	public void getMiembrosZona(DefaultListModel<String> modelListaMiembrosZona,String zona) {
		
		String queryText = String.format("tiene_miembro(%s,X)",zona);
		q = new Query(queryText);	
		
		modelListaMiembrosZona.clear();
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{ 
			System.out.println(res[i].get("X").toString());
			modelListaMiembrosZona.add(i, res[i].get("X").toString());
		}
		
	}


	public boolean addMiembroZona(String miembro, String lugar) {
		
		String queryText = String.format("agregar_miembro_lugar(%s,%s)",miembro,lugar);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
		
	}


	public boolean addElectronicoZona(String electronico, String lugar) {
		String queryText = String.format("agregar_electronico_lugar(%s,%s)",electronico,lugar);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
		
	}


	public void getElectronicosZona(DefaultListModel<String> modelListaElectronicosZona, String zona) {
		
		String queryText = String.format("tiene_electronico(%s,X)",zona);
		q = new Query(queryText);	
		
		modelListaElectronicosZona.clear();
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{ 
			System.out.println(res[i].get("X").toString());
			modelListaElectronicosZona.add(i, res[i].get("X").toString());
		}
		
	}
	
}
