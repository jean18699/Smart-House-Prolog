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
	
	
	public Casa()
	{
		q = new Query("consult('src/main/java/Smart_house.pl')");
		q.hasSolution();
		
	}
	
	public void ActivarModoVisita()
	{
		String queryText = String.format("visitantes(on)");
		q = new Query(queryText);	
		q.open();
		q.getSolution();
		
		
	}
	
	public void DesactivarModoVisita()
	{
		String queryText = String.format("visitantes(off)");
		q = new Query(queryText);	
		q.open();
		q.getSolution();
		
	}
	
	public boolean add_miembro(String nombre)
	{

		String queryText = String.format("nuevo_miembro(%s)", nombre);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}
		
		return false;
		
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
	
	public boolean add_llave(String nombre)
	{

		String queryText = String.format("nueva_llave(%s)", nombre);
		q = new Query(queryText);	
		
		if(q.hasSolution())
		{
			return true;
		}
		
		return false;	
	}
	
	public void getLlavesAgua(DefaultListModel<String> modelListLlaves)
	{
		modelListLlaves.clear();
	
		String queryText = String.format("llave(X,_,_)");
	
		q = new Query(queryText);
		
		Map<String, Term>[] res = q.allSolutions();
		
		for(int i = 0; i < res.length; i++)
		{
			modelListLlaves.add(i, res[i].get("X").toString());
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
	
	
	
	
	public boolean dormir(String nombre) /*throws InterruptedException*/
	{
	
		String queryText = String.format("dormir(%s)", nombre);
		q = new Query(queryText);
				
				
		if(q.hasSolution())
		{					
			return true;
		}else
		{
			return false;
		}

		
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
	
				String queryText = String.format("despertar(%s)", nombre);
				q = new Query(queryText);
				
		
				if(q.hasSolution())
				{					
					return true;
				}else
				{
					return false;
				}

				
	}
	
	
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
	
	
	
	
	public boolean addElectronicoHoras(String nombre, int consumo, int tiempoConsumo)
	{
		String queryText = String.format("nuevo_electronico_horas(%s, %d, %d)", nombre,consumo,tiempoConsumo);
		q = new Query(queryText);	
		if(q.hasSolution())
		{
			return true;
		}else
		{
			return false;
		}
		
	}
	
	public boolean addElectronicoMinutos(String nombre, int consumo, int tiempoConsumo)
	{
		String queryText = String.format("nuevo_electronico_minutos(%s, %d, %d)", nombre,consumo,tiempoConsumo);
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
	
	public float getConsumoElectronico(String nombre)
	{
		String queryText = String.format("consumo(%s,X)",nombre);
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("X").floatValue();
		
	}
	
	public float getConsumoElectronicoTotal()
	{
		String queryText = String.format("get_total_consumo(Total)");
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").floatValue();

	}
	
	public float getPrecioElectricoTotal()
	{
		String queryText = String.format("get_precio_mensual_total_electrico(Precio)");
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Precio").floatValue();

	}
	
	public String getAvisoConsumo()
	{
		String queryText = String.format("sugerencia_energia_utilizada(Sugerencia)");
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Sugerencia").toString();

	}

	public float getConsumoAgua(String llave)
	{
		String queryText = String.format("get_consumo_llave(%s,Total)",llave);
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").floatValue();

	}
	
	public float getConsumoTotalAgua(String llave)
	{
		String queryText = String.format("get_consumo_agua_total(Total)",llave);
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").floatValue();

	}
	
	public int getTiempoLlave(String llave)
	{
		String queryText = String.format("get_tiempo_llave(%s,Tiempo)",llave);
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Tiempo").intValue();

	}
	
	public String getFacturaAgua()
	{
		String queryText = String.format("get_precio_mensual_total_agua(Precio)");
		q = new Query(queryText);	
		q.open();
		
		
		Map<String, Term> res = q.getSolution();
			
		String factura = res.get("Precio").toString();

		return factura;

	}
	
	public void setConsumoAgua(String llave, int tiempoAbierta)
	{
		String queryText = String.format("consumo_llave(%s, %d)",llave,tiempoAbierta);
		q = new Query(queryText);	
		q = new Query(queryText);	
		q.open();
		q.getSolution();
		
	

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
		
		
		modelListPuertas.clear();
		
		Map<String, Term>[] res = q.allSolutions();
	
		for(int i = 0; i < res.length; i++)
		{
			
			
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
		q.open();
	
		Map<String, Term> res = q.getSolution();
		
		return res.get("X").intValue();

		
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
		q.open();
		
		return q.getSolution().get("Mensaje").toString();
			
		
		
		
		
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
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Orientacion").toString();
					
	}
	
	public int getAnguloPanel(String panel) {
	
		String queryText = String.format("panel_solar(%s,_,Angulo,_)",panel);
		q = new Query(queryText);	
		q.open();

		Map<String, Term> res = q.getSolution();
		
		return Integer.parseInt(res.get("Angulo").toString());
				
	}

	public boolean cambiarOrientacionPanel(String panel,String orientacion) {
		
		String queryText = String.format("cambiar_orientacion_panel(%s,%s)",panel,orientacion);
		q = new Query(queryText);	
		q.open();
	
		q.getSolution();
		return true;
			
	}
	
	public boolean cambiarAnguloPanel(String panel,String angulo) {
		
		String queryText = String.format("ajustar_panel(%s,%s)",panel,angulo);
		q = new Query(queryText);	
		q.open();
	
		q.getSolution();
		
		return true;
			
	}

	public String getEnergiaTotalProducida() {
		
		String queryText = String.format("total_energia_generada(X)");
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("X").toString();
			
		
	}

	private void addPanelPruebaNorte()
	{
		String queryText = String.format("nuevo_panel(ejx1586, norte, 15,0)");
		q = new Query(queryText);	
		q.open();
		q.getSolution();
		//return q.getSolution().get("Mensaje").toString();
			
	}
	
	private void addPanelPruebaSur()
	{
		String queryText = String.format("nuevo_panel(ejx1586, sur, 15,0)");
		q = new Query(queryText);	
		q.open();
		q.getSolution();
		//return q.getSolution().get("Mensaje").toString();
			
	}
	
	private void quitarPanelPrueba()
	{
		String queryText = String.format("quitar_panel(ejx1586)");
		q = new Query(queryText);	
		q.open();
		q.getSolution();
		//return q.getSolution().get("Mensaje").toString();
			
	}
	
	
	public String getSugerenciaPanelesNorte() {
		
		String sugerencia = null;
		
		addPanelPruebaNorte();
		
		String queryText = String.format("sugerencia_posicion_perpendicular_panel(ejx1586,Sugerencia)");
		q = new Query(queryText);	
		q.open();
		
		Map<String, Term> res = q.getSolution();
	
		sugerencia = res.get("Sugerencia").toString();
			
		quitarPanelPrueba();
		
		return sugerencia;

	}
	
	public String getSugerenciaPanelesSur()
	{
		String sugerencia = null;
		
		addPanelPruebaSur();
		
		String queryText = String.format("sugerencia_posicion_perpendicular_panel(ejx1586,Sugerencia)");
		q = new Query(queryText);	
		q.open();
				
		Map<String, Term> res = q.getSolution();
		
		sugerencia = res.get("Sugerencia").toString();
			
		quitarPanelPrueba();
		
		return sugerencia;
			
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
		q.open();

		Map<String, Term> res = q.getSolution();
		
		
		return res.get("Capacidad").toString();
			
	}
	
	public String totalAlmacenadoBasurero(String basurero) {
		
		String queryText = String.format("total_almacenado_zafacon(%s,Total)",basurero);
		q = new Query(queryText);	
		q.open();
	

		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").toString();
			
	}

	public String getSugerenciaBasurero(String basurero)
	{
		
		String queryText = String.format("alerta_basura(%s,Sugerencia)",basurero);
		q = new Query(queryText);
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Sugerencia").toString();
			
	}

	public String getTotalDormidos() {
		String queryText = String.format("get_total_dormidos(Total)");
		q = new Query(queryText);
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").toString();

	}

	public String getTotalFuera() {
		String queryText = String.format("get_total_fuera(Total)");
		q = new Query(queryText);
		q.open();
		
		Map<String, Term> res = q.getSolution();
		
		return res.get("Total").toString();

	}

}
