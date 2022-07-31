package App;

import java.util.ArrayList;

import Excepcion.ValidacionException;

public class Transportista {

	private float costoXKm;
	private ArrayList<Cliente> envios;
	private ArrayList<Cliente> clientes;
	
	public Transportista() {
		envios = new ArrayList<>();
		clientes = new ArrayList<>();
		costoXKm = 3;
	}
 

	public String clienteConMasEnvios() {
		String cliente = envios.get(0).toString();
		int cantEnvios = envios.get(0).getCantEnvios();
		for(int i = 0; i < envios.size(); i++) {
			if(cantEnvios<envios.get(i).getCantEnvios()) {
				cantEnvios = envios.get(i).getCantEnvios();
				cliente = envios.get(i).toString();
			}
		}
		return cliente;
	}
	
	//-------------------------------------------------------------ENVIOS
	public float promedioEnvios() {
		float total = 0;
		float promedio;
		int i;
		for(i = 0; i < envios.size(); i++) {
			total += envios.get(i).getCostoAPagar();
		}
		
		promedio = total/i;
		return promedio;
	}
	
	public void confirmacionPedido(float precioTotal, Cliente cliente) {
		cliente.setCostoAPagar(precioTotal);
		cliente.setCantEnvios();
		envios.add(cliente);
	}
	
	public String realizarPedido(Cliente cliente, ArrayList<Producto> productos, float kilometros) {
		String pedido = null;
		pedido = "\nProductos: " + calcularValorProductos(productos);
		pedido += "\nEnvio: " + calcularValorTranslado(kilometros);
		pedido += "\nCosto total: " + calcularCostoTotal(kilometros, cliente, productos);
		return pedido;
	}
	
	//--------------------------------------------------------------CLIENTES
	
	public String mostrarClientes() {
		String rta = null;
		for(int i = 0; i < clientes.size(); i++) {
			rta += "\n" + clientes.get(i).toString();
		}
		return rta;
	}
	
	public Cliente buscar(String nombre) {
		int i = 0;
		while(i<clientes.size() && !nombre.equalsIgnoreCase(clientes.get(i).getNombre())) {
			i++;
		}
		if(i==clientes.size()) {
			return clientes.get(i);
		}else {return null;}
	}
	
	public void agregarCliente(Cliente e) {
		clientes.add(e);
	}
	
	public boolean validacionClienteNombre(String nombre ) throws ValidacionException {
		int i = 0;
		boolean rta;
			while(i<clientes.size() && !clientes.get(i).getNombre().equals(nombre)) {
				i++;
			}
			if(i==clientes.size()) {
				rta = true;
			}else {throw new ValidacionException("Nombre existente");}

		return rta;
	}
	
	//--------------------------------------------------FUNCIONES DE CALCULAR COSTOS
	public float calcularCostoTotal(float kms, Cliente cliente, ArrayList<Producto> productos) {
		float valor = 0;
		valor = calcularValorProductos(productos) + calcularValorTranslado(kms);
		if(cliente instanceof Empresa) {
			float descuento = (float) (valor * 0.10);
			valor = valor - descuento;
		}
		return valor;
	}
	
	public float calcularValorProductos(ArrayList<Producto> productos) {
		float valor = 0;
		for(int i = 0; i < productos.size(); i++) {
			valor += calcularValorProducto(productos.get(i));
		}
		return valor;
	}
	
	public float calcularValorTranslado(float kilometros) {
		return kilometros*costoXKm;
	}
	
	public float calcularValorProducto(Producto p) {
		float valor = 0;
		if(p.getPeso()<10) {
			//Menos de 10 kgs: $15
			valor = 10;
		}else if(p.getPeso()>11 && p.getPeso()<25) {
			//Entre 11 y menos de 25 kgs: $30
			valor = 30;
		}else if(p.getPeso()>25){
			//Más de 25 kgs: $45
			valor = 45;
		}
		return valor;
	}
}
