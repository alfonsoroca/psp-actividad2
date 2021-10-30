package actividad2.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

// Clase que recoge las características y métodos de los objetos Socket
public class SocketCliente {

	// Creación del puerto y la IP a la que nos vamos a conectar
	public static final int PUERTO = 2021;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {

		// Información por consola para identificar al cliente
		System.out.println("              CLIENTE              ");
		System.out.println("-----------------------------------");

		// Encapsulamiento de los datos de la conexión con el servidor
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

		try (Scanner sc = new Scanner(System.in);) {

			// Información por consola para avisar del inicio de la conexión
			System.out.println("CLIENTE: Esperando conexión conexión con el servidor");
			// Creación de objeto Socket() en el cliente
			Socket socketAlServidor = new Socket();
			// Conexión con el servidor en direccionServidor
			socketAlServidor.connect(direccionServidor);
			// Información por consola para informar de la conexión al servidor
			System.out.println("CLIENTE: Conexion IP/PUERTO : " + IP_SERVER + " / " + PUERTO);
			System.out.println("-----------------------------------");

			// Creación de objetos para la comunicación con el servidor
			// Salida de datos del cliente al servidor
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			// Entrada de datos al cliente desde servidor
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);

			String textoConsola = "";
			boolean continuar = true;

			do {
				System.out.println("\nCLIENTE: Elige la opción deseada:");
				System.out.println("			(1) Búsqueda por ISBN");
				System.out.println("			(2) Búsqueda por título");
				System.out.println("			(3) Búsqueda por autor");
				System.out.println("			(4) Añadir libro a la biblioteca");
				System.out.println("			(5) Salir del programa");
				textoConsola = sc.nextLine();

				// Con un switch controlamos las solicitudes del cliente
				switch (textoConsola) {

				case "1":
					// Envío al servidor de la opción elegida por el cliente
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					String respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println("	El libro solicitado es: " + respuesta);

					break;

				case "2":
					// Envío al servidor de la opción elegida por el cliente
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println("	El libro solicitado es: " + respuesta);

					break;

				case "3":
					// Envío al servidor de la opción elegida por el cliente
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();

					// La respuesta puede contener información de uno o más libros por lo que
					// debemos separar las cadenas de texto. Para ello hemos elegido el caracter
					// "-".

					// Array en el que cada elemento contiene la información de cada libros enviada
					// por el servidor en su respuesta.
					String[] libros = respuesta.split("-");

					System.out.println("	La librería dispone de los siguientes libros de " + textoConsola + ":");
					for (String s : libros) {
						System.out.println("		" + s);
					}

					break;

				case "4":
					// Envío al servidor de la opción elegida por el cliente
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor pasándole el ISBN
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor pasándole el título
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor pasándole el autor
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor pasándole el precio
					textoConsola = sc.nextLine();
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println("	Se ha añadido a la librería el libro: " + respuesta);

					break;

				case "5":
					// Envío al servidor de la opción elegida por el cliente
					salida.println(textoConsola);

					// Información por consola
					System.out.println("CLIENTE: En espera de respuesta ......");

					// Lectura y muestra por consola de la respuesta del servidor
					respuesta = entradaBuffer.readLine();
					System.out.println(respuesta);

					// Contestamos al servidor
					System.out.println("Finalizando conexión con el servidor");
					salida.println(textoConsola);

					continuar = false;

					break;

				}
			} while (continuar);

			// Cierre de la conexion con el servidor
			socketAlServidor.close();

		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}

		System.out.println("CLIENTE: Fin del programa");
	}

}
