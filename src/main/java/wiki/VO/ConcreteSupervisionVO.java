package wiki.VO;

import java.util.ArrayList;

import wiki.services.HTMLBlockOperationData;

public class ConcreteSupervisionVO extends SupervisionVO{


	private static final long serialVersionUID = 1L;
	
	// lista de operaciones de transformacion
		private ArrayList<HTMLBlockOperationData> operaciones = new ArrayList<HTMLBlockOperationData>();
		
		private String original;
		private String propuesta;
		public ConcreteSupervisionVO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ConcreteSupervisionVO(int id, int user_id, String username, String titulo_articulo, int articulo_id,
				boolean status) {
			super(id, user_id, username, titulo_articulo, articulo_id, status);
			// TODO Auto-generated constructor stub
		}
		public ConcreteSupervisionVO(ArrayList<HTMLBlockOperationData> operaciones, String original, String propuesta) {
			super();
			this.operaciones = operaciones;
			this.original = original;
			this.propuesta = propuesta;
		}
		public ArrayList<HTMLBlockOperationData> getOperaciones() {
			return operaciones;
		}
		public void setOperaciones(ArrayList<HTMLBlockOperationData> operaciones) {
			this.operaciones = operaciones;
		}
		public String getOriginal() {
			return original;
		}
		public void setOriginal(String original) {
			this.original = original;
		}
		public String getPropuesta() {
			return propuesta;
		}
		public void setPropuesta(String propuesta) {
			this.propuesta = propuesta;
		}
		
		

}
