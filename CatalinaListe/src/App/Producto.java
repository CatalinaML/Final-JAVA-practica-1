package App;

public class Producto {

	private String nombre;
	private float peso;
	private float altura;
	private float ancho;
	
	
	public Producto(String nombre, float peso, float altura, float ancho) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.ancho = ancho;
	}


	public String getNombre() {
		return nombre;
	}


	public float getPeso() {
		return peso;
	}


	public float getAltura() {
		return altura;
	}


	public float getAncho() {
		return ancho;
	}


	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", peso=" + peso + ", altura=" + altura + ", ancho=" + ancho + "]";
	}
	
}
