package actividad2.server;

import java.util.ArrayList;

//Clase que recoge las caracterásticas y métodos de los objetos Libreria
public class Libreria {

	private ArrayList<Libro> libreria;

	// Constructor de una ArrayList<Libro> que añade 5 objetos Libro según
	// requerimiento
	public Libreria() {
		libreria = new ArrayList<Libro>();
		libreria.add(new Libro("1111", "El Quijote", "M.Cervantes", 15.00));
		libreria.add(new Libro("2222", "Dune", "F.Herbert", 20.00));
		libreria.add(new Libro("3333", "1Q84", "H.Murakami", 25.00));
		libreria.add(new Libro("4444", "El nombre de la rosa", "Umberto Eco", 30.00));
		libreria.add(new Libro("5555", "Novelas ejemplares", "M.Cervantes", 35.00)); //

	}

	// Getter y setter
	public ArrayList<Libro> getLibreria() {
		return libreria;
	}

	public void setLibreria(ArrayList<Libro> libreria) {
		this.libreria = libreria;
	}

	// Método para la búsqueda de un objeto Libro por ISBN
	public String getByIsbn(String isbn) {

		String cadena = isbn;
		String resultado = "No existe libro con ese ISBN";

		for (Libro l : libreria) {
			if (cadena.equals(l.getIsbn())) {
				resultado = l.toString();
			}
		}
		return resultado;
	}

	// Método para la búsqueda de un objeto Libro por Título
	public String getByTitulo(String titulo) {

		String cadena = titulo;
		String resultado = "No existe libro con ese título";

		for (Libro l : libreria) {
			if (cadena.equalsIgnoreCase(l.getTitulo())) {
				resultado = l.toString();
			}
		}
		return resultado;
	}

	// Método para añadir libros a la Libreria
	public synchronized void addLibro(Libro libro) {
		libreria.add(libro);

	}

	// Método para la búsqueda de un objeto Libro por autor
	public ArrayList<Libro> getByAutor(String autor) {

		String cadena = autor;
		ArrayList<Libro> resultado = new ArrayList<>();

		for (Libro l : libreria) {
			if (cadena.equalsIgnoreCase(l.getAutor())) {
				resultado.add(l);
			}
		}
		return resultado;
	}

}
