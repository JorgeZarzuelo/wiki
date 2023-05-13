package wiki.entities;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "wikis")
public class Wiki implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String topic;
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "WIKI_ID", foreignKey =@ForeignKey(name="FK_WIKI_ID"))
	private ArrayList<Articulo> articulos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Wiki(int id, String topic, String descripcion, ArrayList<Articulo> articulos) {
		super();
		this.id = id;
		this.topic = topic;
		this.descripcion = descripcion;
		this.articulos = articulos;
	}

	public Wiki() {
		super();
		
	}
	
	
	
	
}
