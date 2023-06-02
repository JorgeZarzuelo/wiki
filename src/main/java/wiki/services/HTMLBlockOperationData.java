package wiki.services;

import wiki.services.HTMLBlockOperationData.Operacion;

public class HTMLBlockOperationData {
	private int initialPosition;
	private int finalPosition;
	private String htmlOriginal;
	private String htmlPropuesto;
	private Operacion operacion;
	
		
	
	public enum Operacion{
		AÑADIR, MODIFICAR, ELIMINAR, MANTENER
	}



	public String getHtmlOriginal() {
		return htmlOriginal;
	}



	public void setHtmlOriginal(String htmlOriginal) {
		this.htmlOriginal = htmlOriginal;
	}



	public String getHtmlPropuesto() {
		return htmlPropuesto;
	}



	public void setHtmlPropuesto(String htmlPropuesto) {
		this.htmlPropuesto = htmlPropuesto;
	}



	public Operacion getOperacion() {
		return operacion;
	}



	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}



	public int getInitialPosition() {
		return initialPosition;
	}



	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}



	public int getFinalPosition() {
		return finalPosition;
	}



	public void setFinalPosition(int finalPosition) {
		this.finalPosition = finalPosition;
	}

}
