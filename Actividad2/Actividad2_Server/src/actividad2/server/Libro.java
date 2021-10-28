package actividad2.server;

// Clase que recoge las características y métodos de los objetos Libro
public class Libro {

	private String isbn, titulo, autor;
	double precio;

	// Constructores
	public Libro() {
		super();
	}

	public Libro(String isbn, String titulo, String autor, double precio) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}

	// Getter y setter
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Libro [ISBN=" + isbn + ", Título=" + titulo + ", Autor=" + autor + ", Precio=" + precio + " euros"
				+ "]";
	}

}
