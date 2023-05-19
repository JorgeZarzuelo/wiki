package wiki.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int user_id;
	
	private int wiki_id;
	
	@Enumerated
	private Tipo tipo;
	
	public static enum Tipo{
		 COORDINADOR, SUPERVISOR
	};
	private boolean pendiente;	
	

	public Rol() {
		super();		
	}	
	
	public Rol( Tipo tipo, boolean pendiente) {
		super();
	
		this.tipo = tipo;
		this.pendiente = pendiente;
	}
	public Rol( Tipo tipo, boolean pendiente, Integer user_id, Integer wiki_id) {
		super();
	    this.user_id = user_id;
		this.tipo = tipo;
		this.pendiente = pendiente;
		this.wiki_id = wiki_id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}
		

	public int getWiki_id() {
		return wiki_id;
	}

	public void setWiki_id(int wiki_id) {
		this.wiki_id = wiki_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
}
