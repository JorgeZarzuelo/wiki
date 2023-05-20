package wiki.entities;

import java.io.Serializable;

import wiki.entities.Rol.Tipo;

public class SolicitudVO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int user_id;
	private String username;
	private int wiki_id;
	private int articulo_id;
	private int rol_id;
	private String titulo_articulo;
	private String topic_wiki;
	private Tipo tipoSolicitud;
	
	public SolicitudVO() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getWiki_id() {
		return wiki_id;
	}

	public void setWiki_id(int wiki_id) {
		this.wiki_id = wiki_id;
	}

	public int getArticulo_id() {
		return articulo_id;
	}

	public void setArticulo_id(int articulo_id) {
		this.articulo_id = articulo_id;
	}

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public String getTitulo_articulo() {
		return titulo_articulo;
	}

	public void setTitulo_articulo(String titulo_articulo) {
		this.titulo_articulo = titulo_articulo;
	}

	public String getTopic_wiki() {
		return topic_wiki;
	}

	public void setTopic_wiki(String topic_wiki) {
		this.topic_wiki = topic_wiki;
	}

	public Tipo getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(Tipo tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	
	

}
