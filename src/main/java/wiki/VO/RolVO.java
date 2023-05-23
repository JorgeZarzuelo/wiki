package wiki.VO;

import java.io.Serializable;

public class RolVO extends SolicitudVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private boolean isPendiente;

	public RolVO() {
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
