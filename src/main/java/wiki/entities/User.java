package wiki.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "usuarios")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", foreignKey =@ForeignKey(name="FK_USER_ID"))
	private List<Rol> roles = new ArrayList<Rol>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", foreignKey =@ForeignKey(name="FK_REVISION_USER_ID"))
	private List<Revision> revisiones = new ArrayList<Revision>();

	public User() {
		super();
	}

	public User(int id, String username, String password, List<Rol> roles, List<Revision> revisiones) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.revisiones = revisiones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Revision> getRevisiones() {
		return revisiones;
	}

	public void setRevisiones(List<Revision> revisiones) {
		this.revisiones = revisiones;
	}
	
	
	
	
}
