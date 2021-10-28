package actividad2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

// Clase que recoge las características y métodos de los objetos SocketServer
public class SocketServer {

	// Creación del puerto por el que escuchará el servidor
	public static final int PUERTO = 2021;

	public static void main(String[] args) {
		
		// Inicializamos la Libreria
		Libreria lb = new Libreria();

		// Información por consola para identificar al servidor y el puerto de escucha
		System.out.println("              SERVER              ");
		System.out.println("SERVER: Escuchando por el puerto " + PUERTO);
		System.out.println("------------------------------------------");

		// Contador de las conexiones solicitadas
		int numConexion = 0;

		try (ServerSocket serverSocket = new ServerSocket()) {

			// Encapsulamiento del puerto del servidor
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);

			// Ponemos al servidor a escuchar por el puerto establecido
			serverSocket.bind(direccion);

			// Bucle infinito para poner al servidor en escucha
			while (true) {				

				// Aceptación de peticiones del cliente
				Socket socketAlCliente = serverSocket.accept();

				// Información por consola de la petición recibida
				System.out.println("SERVER: Atendiendo conexión numero " + ++numConexion);

				// Creamos un nuevo hilo para liberar el hilo principal y atender más peticiones
				new HiloSocketServer(socketAlCliente, lb);				

			}
		} catch (IOException e) {
			System.err.println("SERVER: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVER: Error -> " + e);
			e.printStackTrace();
		}

	}

}
