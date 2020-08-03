package Logico;

public class MiembroFamilia {

	private String nombre;
	private Boolean dormido;
	private Boolean salio;
	
	
	public MiembroFamilia(String nombre)
	{
		this.nombre = nombre;
		this.dormido = false;
		this.salio = false;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getDormido() {
		return dormido;
	}
	public void setDormido(Boolean dormido) {
		this.dormido = dormido;
	}

	public Boolean getSalio() {
		return salio;
	}

	public void setSalio(Boolean salio) {
		this.salio = salio;
	}
	
	
}
