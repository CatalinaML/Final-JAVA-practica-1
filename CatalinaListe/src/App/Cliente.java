package App;

public abstract class Cliente {

	//Atributos
	private String nombre;
	private String origen;
	private String destino;
	private String telefono;
	private float costoAPagar;
	private int cantEnvios;
	
	//Constructor
	public Cliente(String nombre, String origen, String destino, String telefono) {
		super();
		this.nombre = nombre;
		this.origen = origen;
		this.destino = destino;
		this.telefono = telefono;
		costoAPagar = 0;
		cantEnvios = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public float getCostoAPagar() {
		return costoAPagar;
	}
	
	public int getCantEnvios() {
		return cantEnvios;
	}
	
	public void setCostoAPagar(float costoAPagar) {
		this.costoAPagar = costoAPagar;
	}
	
	public void setCantEnvios() {
		this.cantEnvios++;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", origen=" + origen + ", destino=" + destino + ", telefono=" + telefono
				+ "]";
	}
	
}
