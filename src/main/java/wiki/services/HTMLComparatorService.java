package wiki.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import wiki.services.HTMLBlockOperationData.Operacion;
/**
 * Lo primero que se debe hacer es dividir el documento en partes sobre las que queremos controlar los cambios
 * Esto puede hacerse a muchos niveles, existen librerias java que controlan el cambio a nivel de caracter.
 * Implementar una de esas librerias va mucho mas alla del alcance de esta asignatura, y realizar una implementación correcta
 * de una de esas librerias es MUY MUY dificil, estamos trabajando con HTML y parece apropiado que la division sean elementos html
 * 
 * entendemos por elemento html lo que va entre dos etiquetas html (etiquetas incluidas), la funcion "compare" lo primero que hace es dividir el html original
 * y el html propuesto en elementos, para ello utiliza una expresion regular que coge: etiqueta de apertura, cualquier cosa en medio, etiqueta de cierre.
 * 
 * originalParts y propuestoParts almacenan una colección de elementos html, los propuestos y los orginales.
 * 
 * recorriendo los elementos propuestos podemos ver que elementos permanecen y que elementos han cambiado.
 * realmente no nos importan los que han sido eliminados y si han sido editados o son nuevos, 
 * al crear un nuevo Array "finalParts" cargaremos en él las partes que permanecen y las que han sido añadidas (nuevas o modificadas).
 * 
 * el metodo compare genera una lista de operaciones, que convierten el elemento original en el elemento propuesto.
 * el metodo doOperations recibe un Array numérico con las operaciones que debe ejecutar para esa transformación.
 * 
 * Las operaciones son una estructura de datos recogida en la clase HMTLBlockOperationData donde se define el tipo de operación, el elemento html antes y despues, indices...
 * 
 * Este script funciona en un gran porcentaje de casos pero presenta algun bug y es mejorable en muchos puntos, por ejemplo no tiene en cuenta el anidamiento de elementos html
 * y puede fallar en algunas ocasiones, pero tal y como esta diseñado, el acoplamiento es tan bajo que solo habría que trabajar en los métodos de esta clase.
 * 
 * @author JORGE ZARZUELO GUTIERREZ
 *
 */
public class HTMLComparatorService {  
	   
	private String[] originalParts;
	private String[] propuestoParts;
	private String[] finalParts;
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
					   operationData.setOperacion(Operacion.ADD);
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
				case ADD:					
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
	
	/**
	 * Recorre las operaciones indicadas en el parámetro, se inicializa el array finalParts con un valor que nunca podra superar.
	 * Tiene sentido pensar que siempre tendra menos partes que las originales + las propuestas
	 * @param operaciones
	 */
	public void doOperations (Integer[] operaciones) {
		
		this.finalParts = new String[this.propuestoParts.length + this.originalParts.length];	
		
		for (int i=0; i < operaciones.length ; i++) {
			this.doOperation(operaciones[i]);
		}
	
		// limpiamos el array de objetos nulos
		this.finalParts = Arrays.stream(this.finalParts).filter(Objects::nonNull).toArray(String[]::new);
		
	}

	public String[] getFinalParts() {
		return finalParts;
	}
	
	public int getModificationsCount() {
		return this.propuestoParts.length;
	}
	   
	   
	   
	   
	   
	   
}

