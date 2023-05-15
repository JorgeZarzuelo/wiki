package wiki.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "revisiones")
public class Revision implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
		
	private int user_id;
	
	
	private int articulo_id;
	
	@Lob
	private String propuesta;
	
	private boolean pendiente;

	public Revision() {
		super();		
	}

	public Revision(int user_id, int articulo_id, String propuesta, boolean pendiente) {
		super();
		this.user_id = user_id;
		this.articulo_id = articulo_id;
		this.propuesta = propuesta;
		this.pendiente = pendiente;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getArticulo_id() {
		return articulo_id;
	}

	public void setArticulo_id(int articulo_id) {
		this.articulo_id = articulo_id;
	}

	public String getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
