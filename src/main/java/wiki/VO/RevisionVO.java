package wiki.VO;

import java.io.Serializable;
import java.util.ArrayList;

public class RevisionVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private boolean isPendiente;
	
	private int id;
	
	private int articulo_id;
	
	private String contenido;
	
	private String propuesta;
	
	
	private ArrayList<String> contenidoParrafos = new ArrayList<String>();

	
	private ArrayList<String> propuestaParrafos = new ArrayList<String>();
	
	

	public RevisionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isPendiente() {
		return isPendiente;
	}

	public void setPendiente(boolean isPendiente) {
		this.isPendiente = isPendiente;
	}
	
	

}
