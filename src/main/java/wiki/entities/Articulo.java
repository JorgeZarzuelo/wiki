package wiki.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import jakarta.persistence.Id;

@Entity
@Table(name = "articulos")
public class Articulo implements Serializable {
 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titulo;
	private String contenido;
	
	
	
	public Articulo(int id, String titulo, String contenido) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contenido = contenido;
	}
	
	public Articulo( String titulo, String contenido) {
		super();		
		this.titulo = titulo;
		this.contenido = contenido;
	}
	
	
	
	public Articulo() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
	
}
