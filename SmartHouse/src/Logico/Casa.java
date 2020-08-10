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
	
	public String getAvisoConsumo()
	{
		String queryText = String.format("sugerencia_energia_utilizada(Sugerencia)");
		q = new Query(queryText);	
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Sugerencia").toString();

	}
	
	public String getFacturaAgua()
	{
		String queryText = String.format("get_costos_agua(Costo)");
		q = new Query(queryText);	
		
		Map<String, Term> res = q.getSolution();
		
		
		String factura = res.get("Costo").toString();


		return factura;

	}
	
	public String getLitrosTotales()
	{
		String queryText = String.format("total_energia_generada(X)");
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return q.getSolution().get("X").toString();
			
		}else
		{
			return null;
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

	public boolean quitarMiembroZona(String miembro, String lugar) {
		
		String queryText = String.format("quitar_miembro_lugar(%s,%s)",miembro,lugar);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}	
	}

	public boolean quitarElectronicoZona(String electronico, String lugar) {
		
		String queryText = String.format("quitar_electronico_lugar(%s,%s)",electronico,lugar);
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
	
	public boolean programarApagado(String electronico, String tiempo) {
		String queryText = String.format("programar_apagado(%s,%s)",electronico,tiempo);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
		
	}
	
	public int getTemperatura()
	{
		String queryText = String.format("temperaturaHogar(X)");
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return Integer.parseInt(q.getSolution().get("X").toString());
		}else
		{
			return -1;
		}
		
	}
	

	public void getElectronicosZona(DefaultListModel<String> modelListaElectronicosZona, String zona) {
		
		String queryText = String.format("tiene_electronico(%s,X)",zona);
		q = new Query(queryText);	
		
		modelListaElectronicosZona.clear();
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{ 
			modelListaElectronicosZona.add(i, res[i].get("X").toString());
		}
		
	}

	public String getSugerenciaPuertas() {
		
		String queryText = String.format("sugerencia_puertas(Mensaje)");
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return q.getSolution().get("Mensaje").toString();
			
		}
		
		return "N/A";
		
	}
	
	public boolean addPanel(String nombre, String orientacion, String energia)
	{
		String queryText = String.format("nuevo_panel(%s,%s,15,%s)",nombre,orientacion,energia);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
				
	}
	
	public void getPaneles(DefaultListModel<String> modelListaPaneles) {
		
		String queryText = String.format("panel_solar(X,_,_,_)");
		q = new Query(queryText);	
		
		modelListaPaneles.clear();
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{
			modelListaPaneles.add(i, res[i].get("X").toString());
		}
	}

	public String getOrientacionPanel(String panel) {
		
		String queryText = String.format("panel_solar(%s,Orientacion,_,_)",panel);
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return q.getSolution().get("Orientacion").toString();
			
		}else
		{
			return null;
		}
			
	}
	
	public int getAnguloPanel(String panel) {
	
		String queryText = String.format("panel_solar(%s,_,Angulo,_)",panel);
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return Integer.parseInt(q.getSolution().get("Angulo").toString());
			
		}else
		{
			return -1;
		}
			
	}

	public boolean cambiarOrientacionPanel(String panel,String orientacion) {
		
		String queryText = String.format("cambiar_orientacion_panel(%s,%s)",panel,orientacion);
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return true;
			
		}else
		{
			return false;
		}
			
	}
	
	public boolean cambiarAnguloPanel(String panel,String angulo) {
		
		String queryText = String.format("ajustar_panel(%s,%s)",panel,angulo);
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return true;
			
		}else
		{
			return false;
		}
			
	}

	public String getEnergiaTotalProducida() {
		
		String queryText = String.format("total_energia_generada(X)");
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return q.getSolution().get("X").toString();
			
		}else
		{
			return null;
		}
	}

	public String getSugerenciaPanelesNorte() {
		
		String sugerencia = null;
		
		String queryText = String.format("nuevo_panel(ejx1586,norte,15,0)");
		q = new Query(queryText);	
		
		q.next();
		
		queryText = String.format("sugerencia_posicion_perpendicular_panel(ejx1586,Sugerencia)");
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			sugerencia =   q.getSolution().get("Sugerencia").toString();
			
		}
		
		q = new Query("quitar_panel(ejx1586)");
		
		if(q.hasSolution())
		{
			return sugerencia;
		}else
		{
			return null;
		}
		
		
	}
	
	public String getSugerenciaPanelesSur()
	{
		String sugerencia = null;
		
		String queryText = String.format("nuevo_panel(ejx1586,sur,15,0)");
		q = new Query(queryText);	
		
		q.next();
		
		queryText = String.format("sugerencia_posicion_perpendicular_panel(ejx1586,Sugerencia)");
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			sugerencia =   q.getSolution().get("Sugerencia").toString();
			
		}
		
		q = new Query("quitar_panel(ejx1586)");
		
		if(q.hasSolution())
		{
			return sugerencia;
		}else
		{
			return null;
		}
			
	}
	
	public boolean addBasurero(String nombre, String capacidad)
	{
		String queryText = String.format("nuevo_zafacon(%s,%s)",nombre,capacidad);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
				
	}
	
	public boolean addBasura(String basurero, String nombreBasura, String volumen)
	{
		String queryText = String.format("agregar_basura(%s,%s,%s)",basurero,nombreBasura,volumen);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
				
	}
	
	
	public void getBasureros(DefaultListModel<String> modelListBasureros)
	{
		modelListBasureros.clear();
	
		String queryText = String.format("zafacon(X,_,_)");
	
		q = new Query(queryText);
		
		Map<String, Term>[] res = q.allSolutions();
		
		for(int i = 0; i < res.length; i++)
		{
			modelListBasureros.add(i, res[i].get("X").toString());
		}
		
	}

	public String getCapacidadBasurero(String basurero) {
		
		String queryText = String.format("zafacon(%s,Capacidad,_)",basurero);
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return q.getSolution().get("Capacidad").toString();
			
		}else
		{
			return null;
		}
	}
	
	public String totalAlmacenadoBasurero(String basurero) {
		
		String queryText = String.format("total_almacenado_zafacon(%s,Total)",basurero);
		q = new Query(queryText);	
			
	
		if(q.hasSolution())
		{
			return q.getSolution().get("Total").toString();
			
		}else
		{
			return null;
		}
	}

	public String getSugerenciaBasurero(String basurero)
	{
		
	
		String queryText = String.format("alerta_basura(%s,Sugerencia)",basurero);
	
		q = new Query(queryText);
		
		if(q.hasSolution())
		{
			return q.getSolution().get("Sugerencia").toString();
			
		}else
		{
			return null;
		}
		
	}

	
}
