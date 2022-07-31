package App;

import java.util.ArrayList;
import java.util.Scanner;

import Excepcion.ValidacionException;

public class Main {

	static Scanner teclado;
	public static void main(String[] args) {

		teclado = new Scanner(System.in);
		int opcion;
		Transportista translados = new Transportista();
		
		int continuar = 0;
		while(continuar == 0) {
			System.out.println("\n1.Agregar clientes");
			System.out.println("\n2.RealizarPedido");
			System.out.println("\n3.Promediar costos envio");
			System.out.println("\n4.Buscar cliente con mas envios");
			opcion = teclado.nextInt();
			
			switch(opcion) {
			case 1: 
				agregarClientes(translados);
				break;
			case 2:
				//Buscar cliente
				System.out.println("\nNombre cliente: ");
				String nombreCliente = teclado.nextLine();
				teclado.nextLine();
				
				//Cargar productos
				System.out.println("\nProductos: ");
				ArrayList<Producto> productos = new ArrayList<>();
				productos = agregarProductos();
				
				//Cargar cantidad de kilometros
				System.out.println("\nCantidad de kilometros: ");
				float kms = teclado.nextFloat();
				
				if(translados.buscar(nombreCliente)instanceof Particular) {
					Cliente particular = new Particular(null, null, null, null);
					particular = translados.buscar(nombreCliente);

					//Realizar pedido
					String pedido = translados.realizarPedido(particular, productos, kms);
					System.out.println(pedido);
					
					//Confirmacion pedido
					System.out.println("\nPara confirmar presione 1");
					int confirmacion = teclado.nextInt();
					
					if(confirmacion == 1) {
						float precioTotal = translados.calcularCostoTotal(kms, particular, productos);
						translados.confirmacionPedido(precioTotal, particular);
					}else {
						System.out.println("\nPedido cancelado");
					}
					
				}else {
					Cliente empresa = new Empresa(null, null, null, null);
					empresa = translados.buscar(nombreCliente);
					
					//Realizar pedido
					String pedido = translados.realizarPedido(empresa, productos, kms);
					System.out.println(pedido);
					
					//Confirmacion pedido
					System.out.println("\nPara confirmar presione 1");
					int confirmacion = teclado.nextInt();
					
					if(confirmacion == 1) {
						float precioTotal = translados.calcularCostoTotal(kms, empresa, productos);
						translados.confirmacionPedido(precioTotal, empresa);
					}else {
						System.out.println("\nPedido cancelado");
					}
				}
				break;
			case 3:
				float promedio = translados.promedioEnvios();
				System.out.println("\nPromedio en envios: " + promedio);
				
				break;
			case 4:
				System.out.println("\nCliente con mayor cantidad de envios: " + translados.clienteConMasEnvios());
				break;
			}
			System.out.println("\nMENU Continuar = 0");
			continuar = teclado.nextInt();
		}
		teclado.close();
	}
	
	public static ArrayList<Producto> agregarProductos(){
		ArrayList<Producto> productos = new ArrayList<>();
		int continuar = 0;
		while(continuar == 0) {
			System.out.println("\nNombre: ");
			String nombre = teclado.nextLine();
			teclado.nextLine();
			
			System.out.println("\nPeso: ");
			float peso = teclado.nextFloat();
			
			System.out.println("\nAltura: ");
			float altura = teclado.nextFloat();
			
			System.out.println("\nAncho: ");
			float ancho = teclado.nextFloat();
			
			Producto p = new Producto(nombre, peso, altura, ancho);
			productos.add(p);
			
			System.out.println("\n Continuar = 0");
			continuar = teclado.nextInt();
		}
		return productos;
	}
	
	public static void agregarClientes(Transportista t){
		int continuar = 0;
		while(continuar == 0) {
			System.out.println("\nParticular : 1\nEmpresa : 2");
			int tipo = teclado.nextInt();
			
			System.out.println("\nAgregar clientes: ");
			System.out.println("\nNombre: ");
			String nombre = teclado.nextLine();
			teclado.nextLine();
			
			System.out.println("\nDireccion origen: ");
			String origen = teclado.nextLine();
			
			System.out.println("\nDieccion destino: ");
			String destino = teclado.nextLine();
			
			System.out.println("\nTelefono: ");
			String telefono = teclado.nextLine();
			
			try {
				if(tipo == 1) {
					Cliente particular = new Particular(nombre, origen, destino, telefono);
					if(t.validacionClienteNombre(nombre)) {
						t.agregarCliente(particular);
					}
				}else {
					Cliente empresa = new Empresa(nombre, origen, destino, telefono);
					if(t.validacionClienteNombre(nombre)) {
						t.agregarCliente(empresa);
					}
				}
			}catch(ValidacionException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("\n Continuar = 0");
			continuar = teclado.nextInt();
			
		}
	}

}
