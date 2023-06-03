package wiki.VO;

import java.io.Serializable;

public class SupervisionVO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	// id de la revision en concreto
	private int id;
	// quien lo propone
	private int user_id;
	private String username;
	// sobre que articulo
	private String titulo_articulo;
	private int articulo_id;
	// si esta aprobada o no
	private boolean status;
	public SupervisionVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SupervisionVO(int id, int user_id, String username, String titulo_articulo, int articulo_id,
			boolean status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.username = username;
		this.titulo_articulo = titulo_articulo;
		this.articulo_id = articulo_id;
		this.status = status;
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
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitulo_articulo() {
		return titulo_articulo;
	}
	public void setTitulo_articulo(String titulo_articulo) {
		this.titulo_articulo = titulo_articulo;
	}
	public int getArticulo_id() {
		return articulo_id;
	}
	public void setArticulo_id(int articulo_id) {
		this.articulo_id = articulo_id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	

}
