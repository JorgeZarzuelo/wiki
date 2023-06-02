package wiki.services;

import java.util.ArrayList;
import java.util.Arrays;

import wiki.services.HTMLBlockOperationData.Operacion;

public class HTMLComparatorService {  
	   
	private String[] originalParts;
	private String[] propuestoParts;
	private String[] finalParts = new String[10];
	private ArrayList<HTMLBlockOperationData> listaOperaciones = new ArrayList<HTMLBlockOperationData>();
	   
	   public  void compare(String original, String propuesto) {
		 		   
		   
		   originalParts = original.split("(?=[<][a-zA-Z]*[>].*?[<][\\/][a-zA-Z]*[>])");
		   propuestoParts = propuesto.split("(?=[<][a-zA-Z]*[>].*?[<][\\/][a-zA-Z]*[>])");
		   //this.finalParts = new String[Math.max(originalParts.length, propuestoParts.length)];
		   // vamos recorriendo las partes de la propuesta y componiendo bloques de informacion.
		   
		  
		   
		   for (int x=0 ; x < propuestoParts.length ; x++) {
			   
			   HTMLBlockOperationData operationData = new HTMLBlockOperationData();
			   
			  
				   if(Arrays.asList(originalParts).contains(propuestoParts[x])) {
					  int position = Arrays.asList(originalParts).indexOf(propuestoParts[x]);
					  operationData.setOperacion(Operacion.MANTENER);
					  operationData.setHtmlPropuesto(propuestoParts[x]);
					  operationData.setHtmlOriginal(propuestoParts[x]);
					  operationData.setInitialPosition(x);
					  operationData.setFinalPosition(position);
					  
					  listaOperaciones.add(operationData);
				   } 
								   
				   //es nuevo
				   else {
					   operationData.setOperacion(Operacion.AÑADIR);
					   operationData.setHtmlPropuesto(propuestoParts[x]);					   
					   operationData.setInitialPosition(x);
					   operationData.setFinalPosition(x);  					   
					   listaOperaciones.add(operationData);
				   }				   
				   
			   			   
		   }
		   
		   // Buscamos las eliminaciones en el original
		   for (int x=0 ; x < originalParts.length ; x++) {
			   
			   HTMLBlockOperationData operationData = new HTMLBlockOperationData();
			   
			   //Si el original no esta en la propuesta es porque ha sido editado o eliminado
			   if (! Arrays.asList(propuestoParts).contains(originalParts[x])) {
				   operationData.setOperacion(Operacion.ELIMINAR);
				   operationData.setHtmlOriginal(originalParts[x]); 
				   operationData.setInitialPosition(x);
				   operationData.setFinalPosition(x);
				   listaOperaciones.add(operationData);
			   }
			  
			   
			   
		   }
		   
		   this.repararModificaciones();
		  
		  
	 }

	private void repararModificaciones() {
		
		
		
	}

	public ArrayList<HTMLBlockOperationData> getListaOperaciones() {
		return listaOperaciones;
	}


	
	public void doOperation (int _operacion) {
		
		this.listaOperaciones.forEach(operacion -> {
			int index = operacion.getInitialPosition();
			
			if (operacion.getInitialPosition() == _operacion) {
				System.out.println("ejecutando operacion: index: " + index +"/" + this.listaOperaciones.size() + " tamaño finalparts: " + this.finalParts.length);
				switch (operacion.getOperacion()) {
				case ELIMINAR:
					//this.finalParts[index] = null;
					break;				
				case AÑADIR:					
					this.finalParts[index] = operacion.getHtmlPropuesto();
					break;
				case MANTENER:
					this.finalParts[index] = operacion.getHtmlOriginal();
					break;
				case MODIFICAR:
					this.finalParts[index] = operacion.getHtmlPropuesto();	
					break;
				default:
					break;
				}
			}
			
		});
		
	}
	
	public void doOperations (int[] operaciones) {
		
				
		
		for (int i=0; i < operaciones.length ; i++) {
			this.doOperation(operaciones[i]);
		}
		
		
	}

	public String[] getFinalParts() {
		return finalParts;
	}
	
	public int getModificationsCount() {
		return this.propuestoParts.length;
	}
	   
	   
	   
	   
	   
	   
}

