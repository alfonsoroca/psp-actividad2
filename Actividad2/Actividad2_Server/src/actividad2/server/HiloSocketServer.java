package actividad2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

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

	// Constructor con parámetro Socket y Libreria para crear los diferentes hilos
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
					System.out.println("-----------------------------------");
					System.out.println(hilo.getName() + " / petición " + numPeticion
							+ ".- Ha solicitado la búsqueda de un libro por su ISBN y se le han enviado instrucciones.....");

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el ISBN del libro que deseas buscar.....");

					// Recibimos el ISBN del cliente
					isbn = entradaBuffer.readLine();

					// Mostramos la solicitud del cliente
					System.out
							.println(hilo.getName() + " / petición " + numPeticion + ".- Ha enviado el ISBN: " + isbn);

					// Mostramos la información del libro solicitado por el cliente
					System.out.println("	El libro solicitado es: " + lb.getByIsbn(isbn));

					// Enviamos la información solicitada al cliente
					salida.println(lb.getByIsbn(isbn));

					// Mostramos el fin de la petición
					System.out.println("Fin de la petición: " + hilo.getName() + " / petición " + numPeticion);
					System.out.println("-----------------------------------\n");

					break;

				case "2":

					// Mostramos la solicitud del cliente
					System.out.println(hilo.getName() + " / petición " + numPeticion
							+ ".- Ha solicitado la búsqueda de un libro por su título y se le han enviado instrucciones.....");

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el Título del libro que deseas buscar.....");

					// Recibimos el título del cliente
					titulo = entradaBuffer.readLine();

					// Mostramos la solicitud del cliente
					System.out.println(
							hilo.getName() + " / petición " + numPeticion + ".- Ha enviado el título: " + titulo);

					// Mostramos la información del libro solicitado por el cliente
					System.out.println("	El libro solicitado es: " + lb.getByTitulo(titulo));

					// Enviamos la información solicitada al cliente
					salida.println(lb.getByTitulo(titulo));

					// Mostramos el fin de la petición
					System.out.println("Fin de la petición: " + hilo.getName() + " / petición " + numPeticion);
					System.out.println("-----------------------------------\n");

					break;

				case "3":

					// Mostramos la solicitud del cliente
					System.out.println(hilo.getName() + " / petición " + numPeticion
							+ ".- Ha solicitado la búsqueda de los libros de un Autor y se le han enviado instrucciones.....");

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el Autor cuyos libros deseas buscar.....");

					// Recibimos el autor del cliente
					autor = entradaBuffer.readLine();

					// Mostramos la solicitud del cliente
					System.out.println(
							hilo.getName() + " / petición " + numPeticion + ".- Ha enviado el autor: " + autor);

					// El método getByAutor devuelve un ArrayList<Libro> por lo que lo que iteramos
					// su contenido para mandar la respuesta al cliente en forma de cadena.

					ArrayList<Libro> librosAutor = new ArrayList<>();
					librosAutor = lb.getByAutor(autor);
					String libros = "No disponemos de libros de ese autor.";

					// Mostramos la información de los libros del autor solicitado por el cliente y
					// se los enviamos.
					System.out.println("	En la libreria se dispone de los siguientes libros de " + autor + ":");
					if (librosAutor.isEmpty()) {
						System.out.println("		No disponemos de libros de ese autor.");
					}

					// Para separar la información de los diferentes libros elegimos el caracter
					// "-".
					// Mostramos la información por consola y se la añadimos a la cadena que se
					// envía al cliente.
					for (Libro l : librosAutor) {
						System.out.println("		" + l.toString());
						if (libros == "No disponemos de libros de ese autor.") {
							libros = l.toString();
						} else {
							libros += "-";
							libros += l.toString();
						}

					}

					salida.println(libros);

					// Mostramos el fin de la petición
					System.out.println("Fin de la petición: " + hilo.getName() + " / petición " + numPeticion);
					System.out.println("-----------------------------------\n");

					break;

				case "4":

					// Mostramos la solicitud del cliente
					System.out.println(hilo.getName() + " / petición " + numPeticion
							+ ".- Ha solicitado añadir un libro a la libreria y se le han enviado instrucciones.....");

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el ISBN del libro que deseas añadir a la libreria.....");

					// Recibimos el ISBN del cliente
					isbn = entradaBuffer.readLine();

					// Mostramos la solicitud del cliente
					System.out
							.println(hilo.getName() + " / petición " + numPeticion + ".- Ha enviado el ISBN: " + isbn);

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el titulo del libro que deseas añadir a la libreria.....");

					// Recibimos el titulo del cliente
					titulo = entradaBuffer.readLine();

					// Mostramos la solicitud del cliente
					System.out.println(
							hilo.getName() + " / petición " + numPeticion + ".- Ha enviado el título: " + titulo);

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el autor del libro que deseas añadir a la libreria.....");

					// Recibimos el autor del cliente
					autor = entradaBuffer.readLine();

					// Mostramos la solicitud del cliente
					System.out.println(
							hilo.getName() + " / petición " + numPeticion + ".- Ha enviado el autor: " + autor);

					// Enviamos las instrucciones al cliente
					salida.println("Introduce el precio (00.00) del libro que deseas añadir a la libreria.....");

					// Recibimos el precio del cliente que debemos trans
					precio = Double.parseDouble(entradaBuffer.readLine());

					// Con todos los datos recibidos del cliente añadimos el libro a la librería
					lb.addLibro(new Libro(isbn, titulo, autor, precio));

					// Imprimimos los datos del libro añadido y se lo enviamos al cliente.
					System.out.println("	Se ha añadido el siguiente libro a la librería" + lb.getByIsbn(isbn));
					salida.println(lb.getByIsbn(isbn));

					// Mostramos el fin de la petición
					System.out.println("Fin de la petición: " + hilo.getName() + " / petición " + numPeticion + "\n");

					break;

				case "5":

					// Mostramos la solicitud del cliente
					System.out.println("-----------------------------------");
					System.out.println(hilo.getName() + " / petición " + numPeticion
							+ ".- Ha solicitado el cierre de la comunicación");

					// Envío al cliente del aviso de finalización de la comunicación
					salida.println("Finalizando conexión con el servidor");
					
					// Mostramos información por consola
					System.out.println("	Conexión con " + hilo.getName() + " finalizada.");
					

					// Mostramos el fin de la petición
					System.out.println("Fin de la petición: " + hilo.getName() + " / petición " + numPeticion);
					System.out.println("-----------------------------------\n");

					continuar = false;

					break;

				default:

					// El envío de información se controla desde el cliente por lo que este caso no
					// sucede nunca.
					// Mostramos el aviso correspondiente y finalizamos la petición
					System.out
							.println("Se ha recibido una petición no seleccionable por lo que procedemos a su cierre.");
					System.out.println("Fin de la petición: " + hilo.getName() + " / petición " + numPeticion);
					System.out.println("-----------------------------------\n");

					break;

				}
			}

			// Al salir del bucle while() procedemos al cierre del socket
			socketAlCliente.close();

		} catch (IOException e) {
			System.err.println("Hilo: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Hilo: Error -> " + e);
			e.printStackTrace();
		}
	}

}
