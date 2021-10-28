package actividad2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

// Clase que recoge las características y métodos de los objetos R1HiloSocketServer que hacen las veces de hilos del servidor
public class HiloSocketServer implements Runnable {

	// Contador de clientes conectados
	private static int numCliente = 0;
	// Contador de peticiones tramitadas
	private static int numPeticion = 0;
	// Datos de los objetos Libro
	String isbn, titulo, autor;
	double precio;

	private Thread hilo;
	private Socket socketAlCliente;
	private Libreria lb;

	// Constructor con parámetro Socket para crear los diferentes hilos
	public HiloSocketServer(Socket socketAlCliente, Libreria lb) {
		super();
		this.socketAlCliente = socketAlCliente;
		this.lb = lb;
		
		
		// Añadimos un cliente al contador
		numCliente++;
		// Instancia del hilo
		hilo = new Thread(this, "Cliente " + numCliente);

		// Inicialización del hilo instanciado
		hilo.start();
	}

	// Sobrescritura método run()
	@Override
	public void run() {

		/*
		 * PrintStream salida = null; InputStreamReader entrada = null; BufferedReader
		 * entradaBuffer = null;
		 */

		try {

			// Creación de objetos para la comunicación con el cliente
			// Salida de datos del servidor al cliente
			PrintStream salida = new PrintStream(socketAlCliente.getOutputStream());
			// Entrada de datos al servidor desde cliente
			InputStreamReader entrada = new InputStreamReader(socketAlCliente.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);

			String textoEntrante = "";
			boolean continuar = true;

			// Se procesan entradas hasta que el cliente elija la opción 3
			while (continuar) {				
							
				// Añadimos una peticion al contador
				numPeticion++;			

				// Lectura del texto proveniente del cliente
				textoEntrante = entradaBuffer.readLine();

				// Con un switch controlamos las solicitudes del cliente
				switch (textoEntrante) {
			
				case "1":
					
					// Mostramos la solicitud del cliente
					System.out.println(
							hilo.getName() + " petición " + numPeticion + ".- Ha solicitado la búsqueda por ISBN");

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el ISBN del libro que deseas buscar.....");

					// Recibimos el ISBN del cliente
					isbn = entradaBuffer.readLine();
					
					// Mostramos la solicitud del cliente
					System.out.println(hilo.getName() + " petición " + numPeticion + ".- Ha enviado: " + isbn);

					// Mostramos la información del libro solicitado por el cliente
					System.out.println(lb.getByIsbn(isbn));
					
					// Enviamos la información solicitada alcliente
					salida.println(lb.getByIsbn(isbn));
					

					break;

				}

				/*
				 * // Condicional que controla la lógica a realizar por el servidor if
				 * ("3".equals(textoEntrante)) { // Envío al cliente del aviso de finalización
				 * de la comunicación salida.println("Finalizando la comunicación"); //
				 * Información por consola para identificar el cliente y petición que recibirá
				 * // la información System.out.println(hilo.getName() + " petición " +
				 * numPeticion + ".- Ha solicitado el cierre de la comunicación"); continuar =
				 * false; } else { // String textoSaliente = "La opci�n 3 no ha sido elegida";
				 * // Le mandamos la respuesta al cliente salida.println(textoSaliente); //
				 * Información por consola para identificar el cliente y petición que recibirá
				 * // la información System.out.println("SERVER a " + hilo.getName() +
				 * " / petici�n " + numPeticion + ".- La opci�n 3 no ha sido elegida"); }
				 */
			}
			// Al salir del bucle while() procedemos al cierre del socket
			socketAlCliente.close();

		} catch (IOException e) {
			System.err.println("Hilo: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Hilo: Error");
			e.printStackTrace();
		}
	}

}
